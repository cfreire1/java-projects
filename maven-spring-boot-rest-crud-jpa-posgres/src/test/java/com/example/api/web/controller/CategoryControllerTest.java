package com.example.api.web.controller;

import com.example.api.domain.dto.CategoryRequestDTO;
import com.example.api.domain.dto.CategoryResponseDTO;
import com.example.api.domain.services.CategoryServices;
import com.example.api.web.config.handler.exceptions.ApiNotModifiedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryServices categoryServices;

    private CategoryResponseDTO categoryResponseDTO;
    private CategoryRequestDTO categoryRequestDTO;

    @BeforeEach
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //Default

        //Dto
        categoryRequestDTO = objectMapper.readValue(new ClassPathResource("rest/json/CategoryRequest.json").getFile(),
                CategoryRequestDTO.class);
        categoryResponseDTO = objectMapper.readValue(new ClassPathResource("rest/json/CategoryResponse.json").getFile(),
                CategoryResponseDTO.class);

    }

    @Test
    void getAllCategory_ok()  {
        //given (dado) - condición previa o configuracion
        when(categoryServices.getAll()).thenReturn(Arrays.asList(categoryResponseDTO));

        //when (Cuando) - Que vamos a probar
        ResponseEntity<List<CategoryResponseDTO>> listResponseEntity = categoryController.getAll();

        //then (Entonces) - verificacion la salida
        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());

    }

    @Test
    void findBy_ok() {
        //given (dado) - condición previa o configuracion
        when(categoryServices.findBy(1)).thenReturn(Optional.of(categoryResponseDTO));

        //when (Cuando) - Que vamos a probar
        ResponseEntity<CategoryResponseDTO> listResponseEntity = categoryController.findBy(1);

        //then (Entonces) - verificacion la salida
        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());

    }

    @Test
    void save_ok()  {
        //given (dado) - condición previa o configuracion
        when(categoryServices.save(categoryRequestDTO)).thenReturn(Optional.of(categoryRequestDTO));

        //when (Cuando) - Que vamos a probar
        ResponseEntity<CategoryRequestDTO> listResponseEntity = categoryController.save(categoryRequestDTO);

        //then (Entonces) - verificacion la salida
        assertEquals(HttpStatus.CREATED,listResponseEntity.getStatusCode());

    }

    @Test
    void update_ok()  {
        //given (dado) - condición previa o configuracion
        when(categoryServices.update(categoryRequestDTO,1)).thenReturn(Optional.of(categoryRequestDTO));

        //when (Cuando) - Que vamos a probar
        ResponseEntity<CategoryRequestDTO> listResponseEntity = categoryController.update(categoryRequestDTO,1);

        //then (Entonces) - verificacion la salida
        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());

    }

    @Test
    void delete_ok()  {
        //given (dado) - condición previa o configuracion
        when(categoryServices.delete(1)).thenReturn(true);

        //when (Cuando) - Que vamos a probar
        ResponseEntity<CategoryRequestDTO> listResponseEntity = categoryController.delete(1);

        //then (Entonces) - verificacion la salida
        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());

    }

    @Test
    void delete_NOK()  {
        //given (dado) - condición previa o configuracion
        when(categoryServices.delete(1)).thenReturn(false);

        //when (Cuando) - Que vamos a probar
        //then (Entonces) - verificacion la salida
        ApiNotModifiedException apiNotModifiedException
                = assertThrows(// comprobando excepcion lanzada
                ApiNotModifiedException.class ,
                () -> categoryController.delete(1),
                "Expected doThing() to throw, but it didn't"
        );
    }

}
