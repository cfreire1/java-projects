package com.example.javaspringbootrestusers.infrastructure.persistence.mapper;

import com.example.javaspringbootrestusers.domain.model.Users;
import com.example.javaspringbootrestusers.infrastructure.persistence.entity.UsersEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserRepositoryMapperTest {

    @InjectMocks
    private UserRepositoryMapper mapper;

    private Users users;

    private UsersEntity usersEntity;


    @BeforeEach
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //model
        users = objectMapper.readValue(new ClassPathResource("jsonmodel/users.json").getFile(), Users.class);

        //Entity
        usersEntity = objectMapper.readValue(new ClassPathResource("jsonEntity/usersEntity.json").getFile(), UsersEntity.class);
    }

    @Test
    void toDomain_OK(){

        //given (dado) - condición previa o configuracion
        //when (Cuando) - Que vamos a probar
        Users users1 =  mapper.toDomain(usersEntity);

        //then (Entonces) - verificacion la salida
        assertThat(users1).isNotNull();
    }

    @Test
    void toEntity_OK(){
        //given (dado) - condición previa o configuracion
        //when (Cuando) - Que vamos a probar
        UsersEntity usersEntity1  = mapper.toEntity(users);

        //then (Entonces) - verificacion la salida
        assertThat(usersEntity1).isNotNull();
    }

}
