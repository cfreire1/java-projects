package com.example.javaspringbootrestusers.domain;

import com.example.javaspringbootrestusers.application.ports.persistence.PhoneRepository;
import com.example.javaspringbootrestusers.application.ports.persistence.UserRepository;
import com.example.javaspringbootrestusers.application.ports.services.UserService;
import com.example.javaspringbootrestusers.domain.model.Users;
import com.example.javaspringbootrestusers.domain.errorhandler.DomainException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public Users createUser(Users users) {

        // Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya registrado"
        if (userRepository.existEmail(users.getEmail())) {
            throw new DomainException("El correo ya registrado");
        }

        // propu: Si caso el numero telefono conste en la base de datos, retorna un error "El o los telefonos [] se encuentran registrados"
        // Se utiliza Set<> para impedir que se repitan los valores
        Set<Long> listNumRegistred = new HashSet<>();
        users.getPhones().stream().forEach(phone -> {
            if (phoneRepository.existPhone(phone.getNumber())){
                listNumRegistred.add(phone.getNumber());
            }
        });
        if (!listNumRegistred.isEmpty()){
            throw new DomainException("El o los telefonos "+listNumRegistred+" se encuentran registrados");
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
        users.setTokenJwt(Jwts.builder().setIssuedAt(new Date()).compact());

        //=> No se especifica si el usuario debe ingresar por defecto activo o inactivo: default true en 'creacion'.
        //isactive: Indica si el usuario sigue habilitado dentro del sistema.
        users.setActive(true);

        //====================================================================================
        //=> 1. insercion de usuario
        Users users1 = userRepository.saveUser(users);
        users.setIdUser(users1.getIdUser());

        //=> 2. insercion de cada telefono del usuario
        users.getPhones().stream().forEach(phone -> {
            phone.setIdUser(users1.getIdUser());
            phoneRepository.savePhone(phone);
        });

        return users;
    }


    private LocalDateTime getCurrentDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime formatDateTime = LocalDateTime.parse(now.format(formatter), formatter);
        return formatDateTime;
    }



}
