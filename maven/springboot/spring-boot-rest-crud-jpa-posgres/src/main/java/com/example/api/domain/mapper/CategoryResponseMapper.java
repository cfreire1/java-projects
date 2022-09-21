package com.example.api.domain.mapper;

import com.example.api.domain.dto.CategoryResponseDTO;
import com.example.api.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryResponseMapper {
    private CategoryResponseDTO assignToDto(CategoryEntity categoryEntity){
        return CategoryResponseDTO.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .state(categoryEntity.isState())
                .build();
    }


    public List<CategoryResponseDTO> mapperListEntityToDto(List<CategoryEntity> demoProduct) {
        return demoProduct
                .stream()
                .map(this::assignToDto)
                .collect(Collectors.toList());
    }

    public Optional<CategoryResponseDTO> mapperEntityToDto(Optional<CategoryEntity> product) {
        return product.map(this::assignToDto);
    }

}
