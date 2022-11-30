package com.example.javaspringbootrestusers.domain;

import com.example.javaspringbootrestusers.application.ports.persistence.PhoneRepository;
import com.example.javaspringbootrestusers.application.ports.persistence.UserRepository;
import com.example.javaspringbootrestusers.domain.model.Users;
import com.example.javaspringbootrestusers.domain.errorhandler.DomainException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    private Users users;


    @BeforeEach
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //model
        users = objectMapper.readValue(new ClassPathResource("jsonmodel/users.json").getFile(), Users.class);


    }

    @Test
    void createUser_OK(){

        //given (dado) - condición previa o configuracion
        users.setIdUser(UUID.randomUUID());
        when(userRepository.saveUser(any(Users.class))).thenReturn(users);

        //when (Cuando) - Que vamos a probar
        Users users1 = userService.createUser(users,"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTY2OTc5NDk4MSwiZXhwIjoxNjY5Nzk1MDQxfQ.hmgr8IrnjvsNQMnRdhVKJKJcFkQFXJ-nrB9_87k54jg");

        //then (Entonces) - verificacion la salida
        assertThat(users1).isNotNull();
    }

    @Test
    void errorEmailYaRegistrado(){
        //given (dado) - condición previa o configuracion
        when(userRepository.existEmail(any(String.class))).thenReturn(true);

        //when (Cuando) - Que vamos a probar
        //then (Entonces) - verificacion la salida
        DomainException apiRestException = assertThrows
                (DomainException.class,
                        () -> userService.createUser(users,"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTY2OTc5NDk4MSwiZXhwIjoxNjY5Nzk1MDQxfQ.hmgr8IrnjvsNQMnRdhVKJKJcFkQFXJ-nrB9_87k54jg"),
                        "El correo ya registrado");
    }

    @Test
    void errorTelefonosYaRegistrados(){
        //given (dado) - condición previa o configuracion
        when(phoneRepository.existPhone(any(long.class))).thenReturn(true);

        //when (Cuando) - Que vamos a probar
        //then (Entonces) - verificacion la salida
        DomainException apiRestException = assertThrows
                (DomainException.class,
                        () -> userService.createUser(users,"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTY2OTc5NDk4MSwiZXhwIjoxNjY5Nzk1MDQxfQ.hmgr8IrnjvsNQMnRdhVKJKJcFkQFXJ-nrB9_87k54jg"),
                        "El correo ya registrado");
    }
}
