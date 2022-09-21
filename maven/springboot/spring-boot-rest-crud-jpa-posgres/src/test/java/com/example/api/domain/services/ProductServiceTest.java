package com.example.api.domain.services;

import com.example.api.domain.dto.CategoryResponseDTO;
import com.example.api.domain.dto.ProductRequestDTO;
import com.example.api.domain.dto.ProductResponseDTO;
import com.example.api.domain.mapper.ProductRequestMapper;
import com.example.api.domain.mapper.ProductResponseMapper;
import com.example.api.persistence.ProductRepository;
import com.example.api.persistence.entity.CategoryEntity;
import com.example.api.persistence.entity.ProductEntity;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl productServices;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductResponseMapper productResponseMapper;

    @Mock
    private ProductRequestMapper productRequestMapper;

    private ProductRequestDTO productRequestDTO;
    private ProductResponseDTO productResponseDTO;
    private ProductEntity productEntity;

    @BeforeEach
    public void init() throws IOException {
        //Default
        ObjectMapper objectMapper = new ObjectMapper();

        //Entity
        productEntity = objectMapper.readValue(new ClassPathResource("rest/json/ProductResponse.json").getFile(),
                ProductEntity.class);
        CategoryEntity categoryEntity = objectMapper.readValue(new ClassPathResource("rest/json/CategoryResponse.json").getFile(),
                CategoryEntity.class);
        productEntity.setDemoCategory(categoryEntity);


        //Dto
        productRequestDTO = objectMapper.readValue(new ClassPathResource("rest/json/ProductRequest.json").getFile(),
                ProductRequestDTO.class);


        productResponseDTO = objectMapper.readValue(new ClassPathResource("rest/json/ProductResponse.json").getFile(),
                ProductResponseDTO.class);
        CategoryResponseDTO categoryResponseDTO = objectMapper.readValue(new ClassPathResource("rest/json/CategoryResponse.json").getFile(),
                CategoryResponseDTO.class);
        productResponseDTO.setDemoCategoryDTO(categoryResponseDTO);

    }

    @Test
    void getAllProduct_ok() {
        //given (dado) - condición previa o configuracion
        when(productRepository.getAll()).thenReturn(Arrays.asList(productEntity));
        when(productResponseMapper.mapperListEntityToDto(any(List.class))).thenReturn(Arrays.asList(productResponseDTO));

        //when (Cuando) - Que vamos a probar
        when(productServices.getAll()).thenReturn(Arrays.asList(productResponseDTO));

        //then (Entonces) - verificacion la salida
        assertThat(productServices.getAll()).isNotNull();
    }

    @Test
    void getFindBy_ok() {
        //given (dado) - condición previa o configuracion
        when(productRepository.findBy(1)).thenReturn(Optional.of(productEntity));
        when(productResponseMapper.mapperEntityToDto(any(Optional.class))).thenReturn(Optional.of(productResponseDTO));
        when(productServices.findBy(1)).thenReturn(Optional.of(productResponseDTO));

        //when (Cuando) - Que vamos a probar
        Optional<ProductResponseDTO> productResponseDTO1 = productServices.findBy(1);

        //then (Entonces) - verificacion la salida
        productResponseDTO1
                .map(productResponseDTO2 -> assertThat(productResponseDTO2).isNotNull());
    }

    @Test
    void save_ok() {
        //given (dado) - condición previa o configuracion
        when(productRepository.save(any(ProductEntity.class))).thenReturn(Optional.of(productEntity));
        when(productRequestMapper.mapperDtoToEntity(any(ProductRequestDTO.class))).thenReturn(productEntity);
        when(productRequestMapper.mapperEntityToDto(any(Optional.class))).thenReturn(Optional.of(productRequestDTO));
        when(productServices.save(productRequestDTO)).thenReturn(Optional.of(productRequestDTO));

        //when (Cuando) - Que vamos a probar
        Optional<ProductRequestDTO> productRequestDTO1 = productServices.save(productRequestDTO);

        //then (Entonces) - verificacion la salida
        assertThat(productRequestDTO1.map(productRequestDTO2 -> productRequestDTO2.getName()).orElse("")).isEqualTo("NOTEBOOK");
    }

    @Test
    void update_ok() {
        //given (dado) - condición previa o configuracion
        when(productRepository.save(any(ProductEntity.class))).thenReturn(Optional.of(productEntity));
        when(productRequestMapper.mapperDtoToEntity(productEntity, productRequestDTO)).thenReturn(productEntity);
        when(productRequestMapper.mapperEntityToDto(any(Optional.class))).thenReturn(Optional.of(productRequestDTO));
        when(productRepository.findBy(1)).thenReturn(Optional.of(productEntity));
        when(productServices.update(productRequestDTO,1)).thenReturn(Optional.of(productRequestDTO));

        //when (Cuando) - Que vamos a probar
        Optional<ProductRequestDTO> productRequestDTO1 = productServices.update(productRequestDTO,1);

        //then (Entonces) - verificacion la salida
        assertThat(productRequestDTO1.map(productRequestDTO2 -> productRequestDTO2.getName()).orElse("")).isEqualTo("NOTEBOOK");
    }

    @Test
    void delete_ok() {
        //given (dado) - condición previa o configuracion
        when(productRepository.findBy(1)).thenReturn(Optional.of(productEntity));
        when(productResponseMapper.mapperEntityToDto(Optional.of(productEntity))).thenReturn(Optional.of(productResponseDTO));
        when(productServices.findBy(1)).thenReturn(Optional.of(productResponseDTO));

        //when (Cuando) - Que vamos a probar
        doNothing().when(productRepository).delete(1);

        //then (Entonces) - verificacion la salida
        assertEquals(true, productServices.delete(1));
        assertEquals(false, productServices.delete(2));
    }
    
}
