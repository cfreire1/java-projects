package com.example.msbackendusers.domain.usecase;

import com.example.msbackendusers.application.ports.services.UserService;
import com.example.msbackendusers.domain.model.Users;
import com.example.msbackendusers.infrastructure.controller.dto.UsersRequestDTO;
import com.example.msbackendusers.infrastructure.controller.dto.UsersResponseDTO;
import com.example.msbackendusers.infrastructure.errorhandler.GeneralException;
import com.example.msbackendusers.infrastructure.mapper.UserControllerMapper;
import com.example.msbackendusers.infrastructure.repository.UserRestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserUseCaseImpl implements UserService {

    @Autowired
    private UserRestServices userRestServices;

    @Autowired
    private UserControllerMapper mapper;

    @Override
    public UsersResponseDTO createUser(UsersRequestDTO usersDto, String token) {
        valitadateObj(usersDto);

        //============================================== mapper manual
        Users users = mapper.toDomain(usersDto);

        //=============================================+ preparar a servicio

        // Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya registrado"
        if (userRestServices.existEmail(users.getEmail())) {
            throw new GeneralException("El correo ya registrado");
        }

        // propu: Si caso el numero telefono conste en la base de datos, retorna un error "El o los telefonos [] se encuentran registrados"
        // Se utiliza Set<> para impedir que se repitan los valores
        Set<Long> listNumRegistred = new HashSet<>();
        users.getPhones().stream().forEach(phone -> {
            if (userRestServices.existPhone(phone.getNumber())){
                listNumRegistred.add(phone.getNumber());
            }
        });
        if (!listNumRegistred.isEmpty()){
            throw new GeneralException("El o los telefonos "+listNumRegistred+" se encuentran registrados");
        }

        //=============================================================================================================
        //=> Como es un nuevo ingreso considere que se manejaran la misma fecha en los 3 atributos
        //created: fecha de creación del usuario
        //modified: fecha de la última actualización de usuario
        //last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha de creación)
        users.setCreated(getCurrentDateTime());
        users.setModified(getCurrentDateTime());
        users.setLastLogin(getCurrentDateTime());

        //token: token de acceso de la API (puede ser UUID o JWT)
        users.setTokenJwt(token);

        //=> No se especifica si el usuario debe ingresar por defecto activo o inactivo: default true en 'creacion'.
        //isactive: Indica si el usuario sigue habilitado dentro del sistema.
        users.setActive(true);

        //====================================================================================
        //=> 1. insercion de usuario
        Users users1 = userRestServices.saveUser(users);
        users.setIdUser(users1.getIdUser());

        //=> 2. insercion de cada telefono del usuario
        users.getPhones().stream().forEach(phone -> {
            phone.setIdUser(users1.getIdUser());
            userRestServices.savePhone(phone);
        });

        return mapper.toDTO(users);
    }

    public void valitadateObj(UsersRequestDTO users){
        //La clave debe seguir una expresión regular para validar que formato sea el correcto.
        //(Una Mayuscula, letras minúsculas, y dos numeros)
        //Explicacion regex:
        // 1grupo(Primera palabra mayuscula),
        // 2grupo(resto en minuscula),
        // 3grupo(maximo 2 numeros)
        if (!users.getPassword().matches("^([A-Z])+([a-z])+([0-9]{2})")){
            throw new GeneralException("La password no tiene un formato correcto" +
                    " [Debe contener Una mayuscula, letras minúsculas, y dos numeros].");
        }

        //El correo debe seguir una expresión regular para validar que formato sea el correcto.(aaaaaaa@dominio.cl)
        //Explicacion regex:
        // 1grupo(Resto de letras Mayuscu y minusculas y numeros hasta antes del @),
        // 2grupo(almenos 1 @),
        // 3grupo(Resto de letras Mayuscu y minusculas y numeros hasta antes del . ),
        // 4grupo(entiendo que al colocar \. me permite que identifique el punto)
        // 5grupo(Resto de letras Mayuscu y minusculas)
        if (!users.getEmail().matches("([A-Za-z0-9])+(@)([A-Za-z0-9])+(\\.)([A-Za-z])+")){
            throw new GeneralException("El eMail no tiene un formato correcto.");
        }
    }

    private LocalDateTime getCurrentDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime formatDateTime = LocalDateTime.parse(now.format(formatter), formatter);
        return formatDateTime;
    }

}
