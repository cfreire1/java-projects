package com.example.api.domain.mapper;

import com.example.api.domain.dto.CategoryRequestDTO;
import com.example.api.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryRequestMapper {

    public CategoryEntity mapperDtoToEntity(CategoryRequestDTO categoryRequestDTO) {
        return CategoryEntity.builder()
                .name(categoryRequestDTO.getName())
                .state(categoryRequestDTO.isState())
                .build();
    }

    public CategoryEntity mapperDtoToEntity(CategoryEntity categoryEntity , CategoryRequestDTO categoryRequestDTO) {
        categoryEntity.setName(categoryRequestDTO.getName());
        categoryEntity.setState(categoryRequestDTO.isState());
        return categoryEntity;
    }

    public Optional<CategoryRequestDTO> mapperEntityToDto(Optional<CategoryEntity> categoryEntity) {
        return categoryEntity.map(categoryEntity1 ->
                CategoryRequestDTO.builder()
                        .id(Optional.ofNullable(categoryEntity1.getId()).orElse(null))
                        .name(categoryEntity1.getName())
                        .state(categoryEntity1.isState())
                        .build());
    }
}
