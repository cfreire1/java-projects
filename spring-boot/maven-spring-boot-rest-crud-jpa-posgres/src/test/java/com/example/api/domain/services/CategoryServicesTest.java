package com.example.api.domain.services;

import com.example.api.domain.dto.CategoryRequestDTO;
import com.example.api.domain.dto.CategoryResponseDTO;
import com.example.api.domain.mapper.CategoryRequestMapper;
import com.example.api.domain.mapper.CategoryResponseMapper;
import com.example.api.persistence.CategoryRepository;
import com.example.api.persistence.entity.CategoryEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServicesTest {

    @InjectMocks
    private CategoryServicesImpl categoryServices;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryResponseMapper categoryResponseMapper;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    private CategoryRequestDTO categoryRequestDTO;
    private CategoryResponseDTO categoryResponseDTO;
    private CategoryEntity categoryEntity;

    @BeforeEach
    public void init() throws IOException {
        //Default
        ObjectMapper objectMapper = new ObjectMapper();

        //Entity
        categoryEntity = objectMapper.readValue(new ClassPathResource("rest/json/CategoryResponse.json").getFile(),
                CategoryEntity.class);

        //Dto
        categoryRequestDTO = objectMapper.readValue(new ClassPathResource("rest/json/CategoryRequest.json").getFile(),
                CategoryRequestDTO.class);
        categoryResponseDTO = objectMapper.readValue(new ClassPathResource("rest/json/CategoryResponse.json").getFile(),
                CategoryResponseDTO.class);

    }

    @Test
    void getAllCategory_ok() {
        //given (dado) - condición previa o configuracion
        when(categoryRepository.getAll()).thenReturn(Arrays.asList(categoryEntity));
        when(categoryResponseMapper.mapperListEntityToDto(any(List.class))).thenReturn(Arrays.asList(categoryResponseDTO));

        //when (Cuando) - Que vamos a probar
        when(categoryServices.getAll()).thenReturn(Arrays.asList(categoryResponseDTO));

        //then (Entonces) - verificacion la salida
        assertThat(categoryServices.getAll()).isNotNull();
    }

    @Test
    void getFindBy_ok() {
        //given (dado) - condición previa o configuracion
        when(categoryRepository.findBy(1)).thenReturn(Optional.of(categoryEntity));
        when(categoryResponseMapper.mapperEntityToDto(any(Optional.class))).thenReturn(Optional.of(categoryResponseDTO));
        when(categoryServices.findBy(1)).thenReturn(Optional.of(categoryResponseDTO));

        //when (Cuando) - Que vamos a probar
        Optional<CategoryResponseDTO> categoryResponseDTO1 = categoryServices.findBy(1);

        //then (Entonces) - verificacion la salida
        categoryResponseDTO1
                .map(categoryResponseDTO2 -> assertThat(categoryResponseDTO2).isNotNull());
    }

    @Test
    void save_ok() {
        //given (dado) - condición previa o configuracion
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(Optional.of(categoryEntity));
        when(categoryRequestMapper.mapperDtoToEntity(any(CategoryRequestDTO.class))).thenReturn(categoryEntity);
        when(categoryRequestMapper.mapperEntityToDto(any(Optional.class))).thenReturn(Optional.of(categoryRequestDTO));
        when(categoryServices.save(categoryRequestDTO)).thenReturn(Optional.of(categoryRequestDTO));

        //when (Cuando) - Que vamos a probar
        Optional<CategoryRequestDTO> categoryRequestDTO1 = categoryServices.save(categoryRequestDTO);

        //then (Entonces) - verificacion la salida
        assertThat(categoryRequestDTO1.map(categoryRequestDTO2 -> categoryRequestDTO2.getName()).orElse("")).isEqualTo("TECNOLOGIA");
    }

    @Test
    void update_ok() {
        //given (dado) - condición previa o configuracion
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(Optional.of(categoryEntity));
        when(categoryRequestMapper.mapperDtoToEntity(categoryEntity,categoryRequestDTO)).thenReturn(categoryEntity);
        when(categoryRequestMapper.mapperEntityToDto(any(Optional.class))).thenReturn(Optional.of(categoryRequestDTO));
        when(categoryRepository.findBy(1)).thenReturn(Optional.of(categoryEntity));
        when(categoryServices.update(categoryRequestDTO,1)).thenReturn(Optional.of(categoryRequestDTO));

        //when (Cuando) - Que vamos a probar
        Optional<CategoryRequestDTO> categoryRequestDTO1 = categoryServices.update(categoryRequestDTO,1);

        //then (Entonces) - verificacion la salida
        assertThat(categoryRequestDTO1.map(categoryRequestDTO2 -> categoryRequestDTO2.getName()).orElse("")).isEqualTo("TECNOLOGIA");
    }

    @Test
    void delete_ok() {
        //given (dado) - condición previa o configuracion
        when(categoryRepository.findBy(1)).thenReturn(Optional.of(categoryEntity));
        when(categoryResponseMapper.mapperEntityToDto(Optional.of(categoryEntity))).thenReturn(Optional.of(categoryResponseDTO));
        when(categoryServices.findBy(1)).thenReturn(Optional.of(categoryResponseDTO));

        //when (Cuando) - Que vamos a probar
        doNothing().when(categoryRepository).delete(1);

        //then (Entonces) - verificacion la salida
        assertEquals(true,categoryServices.delete(1));
        assertEquals(false,categoryServices.delete(2));
    }

}
