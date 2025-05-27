package com.example.api.domain.services;

import com.example.api.domain.dto.UsernameDTO;
import com.example.api.domain.mapper.UsernameMapper;
import com.example.api.persistence.UsernameRepository;
import com.example.api.persistence.entity.UsernameEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UsernameRepository usernameRepository;

    @Mock
    private UsernameMapper usernameMapper;

    private UsernameDTO usernameDTO;
    private UsernameEntity usernameEntity;

    @BeforeEach
    public void init() throws IOException {
        //Default
        ObjectMapper objectMapper = new ObjectMapper();

        //Entity
        usernameEntity = UsernameEntity.builder()
                .username("juan")
                .password("$2a$10$WVcYE5QxhVLpN9ViXaATcO6hOJLk9RkQYuuAwxNJfTNXiU37WThUq")
                .role("CART")
                .state(true)
                .build();


        //Dto
        usernameDTO = UsernameDTO.builder()
                .username("juan")
                .password("$2a$10$WVcYE5QxhVLpN9ViXaATcO6hOJLk9RkQYuuAwxNJfTNXiU37WThUq")
                .role("CART")
                .state(true)
                .build();

    }


    @Test
    public void loadUserByUsername_OK(){
        //given (dado) - condición previa o configuracion
        when(usernameRepository.findUsername("juan")).thenReturn(Optional.of(usernameEntity));
        when(usernameMapper.mapperEntityToDto(Optional.of(usernameEntity))).thenReturn(Optional.of(usernameDTO));

        //when (Cuando) - Que vamos a probar
        UserDetails userDetails1 = userDetailsService.loadUserByUsername("juan");

        //then (Entonces) - verificacion la salida
        assertEquals("juan",userDetails1.getUsername());

    }


    @Test
    public void loadUserByUsername_NOK(){

        //given (dado) - condición previa o configuracion
        when(usernameRepository.findUsername("felipe")).thenReturn(Optional.empty());
        when(usernameMapper.mapperEntityToDto(Optional.empty())).thenReturn(Optional.empty());

        //when (Cuando) - Que vamos a probar

        UsernameNotFoundException usernameNotFoundException
                = assertThrows(// comprobando excepcion lanzada
                        UsernameNotFoundException.class ,
                () -> userDetailsService.loadUserByUsername("felipe"),
                "Expected doThing() to throw, but it didn't"
        );

        //then (Entonces) - verificacion la salida

    }

}
