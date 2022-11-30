package com.example.javaspringbootrestusers.infrastructure.controller;

import com.example.javaspringbootrestusers.application.ports.services.UserService;
import com.example.javaspringbootrestusers.domain.errorhandler.DomainException;
import com.example.javaspringbootrestusers.infrastructure.controller.dto.UsersRequestDTO;
import com.example.javaspringbootrestusers.infrastructure.controller.dto.UsersResponseDTO;
import com.example.javaspringbootrestusers.infrastructure.controller.mapper.UserControllerMapper;
import com.example.javaspringbootrestusers.infrastructure.errorhandler.GeneralException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserControllerMapper mapper;

    @PostMapping("/create-user")
    public ResponseEntity<UsersResponseDTO> createUser(@RequestHeader("Authorization") String token, @RequestBody UsersRequestDTO usersRequestDTO){

        //La clave debe seguir una expresión regular para validar que formato sea el correcto.
        //(Una Mayuscula, letras minúsculas, y dos numeros)
        //Explicacion regex:
        // 1grupo(Primera palabra mayuscula),
        // 2grupo(resto en minuscula),
        // 3grupo(maximo 2 numeros)
        if (!usersRequestDTO.getPassword().matches("^([A-Z])+([a-z])+([0-9]{2})")){
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
        if (!usersRequestDTO.getEmail().matches("([A-Za-z0-9])+(@)([A-Za-z0-9])+(\\.)([A-Za-z])+")){
            throw new GeneralException("El eMail no tiene un formato correcto.");
        }

        UsersResponseDTO usersResponseDTO = mapper.toDTO(userService.createUser(mapper.toDomain(usersRequestDTO),token));
        return new ResponseEntity<>(usersResponseDTO, HttpStatus.OK);
    }
}
