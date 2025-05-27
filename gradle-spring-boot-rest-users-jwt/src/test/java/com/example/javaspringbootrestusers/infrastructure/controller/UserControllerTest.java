package com.example.javaspringbootrestusers.infrastructure.controller;

import com.example.javaspringbootrestusers.application.ports.services.UserService;
import com.example.javaspringbootrestusers.domain.errorhandler.DomainException;
import com.example.javaspringbootrestusers.domain.model.Users;
import com.example.javaspringbootrestusers.infrastructure.controller.dto.UsersRequestDTO;
import com.example.javaspringbootrestusers.infrastructure.controller.dto.UsersResponseDTO;
import com.example.javaspringbootrestusers.infrastructure.controller.mapper.UserControllerMapper;
import com.example.javaspringbootrestusers.infrastructure.errorhandler.GeneralException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserControllerMapper mapper;

    private UsersRequestDTO usersRequestDTO;
    private Users users;
    private UsersResponseDTO usersResponseDTO;


    @BeforeEach
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //DTO
        usersRequestDTO = objectMapper.readValue(new ClassPathResource("jsoncontroller/userRequestDTO.json").getFile(), UsersRequestDTO.class);
        usersResponseDTO = objectMapper.readValue(new ClassPathResource("jsoncontroller/userResponseDTO.json").getFile(), UsersResponseDTO.class);
        usersResponseDTO.setCreated(LocalDateTime.parse("2022-08-21T02:27:35"));
        usersResponseDTO.setModified(LocalDateTime.parse("2022-08-21T02:27:35"));
        usersResponseDTO.setLastLogin(LocalDateTime.parse("2022-08-21T02:27:35"));

        //model
        users = objectMapper.readValue(new ClassPathResource("jsonmodel/users.json").getFile(), Users.class);


    }

    @Test
    void endPointCreateUser_OK(){
        //given (dado) - condición previa o configuracion
        when(mapper.toDomain(usersRequestDTO)).thenReturn(users);
        when(userService.createUser(any(Users.class),"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTY2OTc5NDk4MSwiZXhwIjoxNjY5Nzk1MDQxfQ.hmgr8IrnjvsNQMnRdhVKJKJcFkQFXJ-nrB9_87k54jg")).thenReturn(users);
        when(mapper.toDTO(any(Users.class))).thenReturn(usersResponseDTO);

        //when (Cuando) - Que vamos a probar
        ResponseEntity<UsersResponseDTO> response = userController.createUser("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTY2OTc5NDk4MSwiZXhwIjoxNjY5Nzk1MDQxfQ.hmgr8IrnjvsNQMnRdhVKJKJcFkQFXJ-nrB9_87k54jg", usersRequestDTO);

        //then (Entonces) - verificacion la salida
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }


    @Test
    void errorClave(){
        //given (dado) - condición previa o configuracion
        usersRequestDTO.setPassword("asfdf");

        //when (Cuando) - Que vamos a probar
        //then (Entonces) - verificacion la salida
        GeneralException apiRestException = assertThrows
                (GeneralException.class,
                        () -> userController.createUser("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTY2OTc5NDk4MSwiZXhwIjoxNjY5Nzk1MDQxfQ.hmgr8IrnjvsNQMnRdhVKJKJcFkQFXJ-nrB9_87k54jg", usersRequestDTO),
                        "La password no tiene un formato correcto" +
                                " [Debe contener Una mayuscula, letras minúsculas, y dos numeros].");
    }

    @Test
    void errorEmail(){
        //given (dado) - condición previa o configuracion
        usersRequestDTO.setEmail("asfdf");

        //when (Cuando) - Que vamos a probar
        //then (Entonces) - verificacion la salida
        GeneralException apiRestException = assertThrows
                (GeneralException.class,
                        () -> userController.createUser("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTY2OTc5NDk4MSwiZXhwIjoxNjY5Nzk1MDQxfQ.hmgr8IrnjvsNQMnRdhVKJKJcFkQFXJ-nrB9_87k54jg", usersRequestDTO),
                        "El eMail no tiene un formato correcto.");
    }
}
