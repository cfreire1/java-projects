package com.example.springbootrestbasic.domain.dummy;

import com.example.springbootrestbasic.domain.dto.CategoryRequestDTO;
import com.example.springbootrestbasic.domain.dto.CategoryResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataDummy {
    public CategoryResponseDTO dummyCategoryResponseDTO() {
        return CategoryResponseDTO.builder()
                .id(1)
                .name("name")
                .state(true)
                .build();
    }
    public Optional<CategoryResponseDTO> dummyCategoryResponseDTOOptional(){
        return Optional.of(dummyCategoryResponseDTO());
    }

    public List<CategoryResponseDTO> dummyCategoryResponseDTOList(){
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        categoryResponseDTOS.add(dummyCategoryResponseDTO());
        return categoryResponseDTOS;
    }

    //=================================================================

    public CategoryRequestDTO dummyCategoryRequestDTO() {
        return CategoryRequestDTO.builder()
                .id(1)
                .name("name")
                .state(true)
                .build();
    }


    public Optional<CategoryRequestDTO> dummyCategoryRequestDTOOptional() {
        return Optional.of(dummyCategoryRequestDTO());
    }
}
