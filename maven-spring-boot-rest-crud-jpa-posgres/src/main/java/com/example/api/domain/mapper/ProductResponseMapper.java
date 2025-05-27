package com.example.api.domain.mapper;

import com.example.api.domain.dto.CategoryResponseDTO;
import com.example.api.domain.dto.ProductResponseDTO;
import com.example.api.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * https://mkyong.com/java8/java-8-streams-map-examples/
 */
@Component
public class ProductResponseMapper {

    private ProductResponseDTO assignToDto(ProductEntity product){
        return ProductResponseDTO.builder()
                .id(product.getId())
                .idCategory(product.getIdCategory())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .createAt(product.getCreateAt())
                .state(product.isState())
                .demoCategoryDTO(CategoryResponseDTO.builder()
                        .id(product.getDemoCategory().getId())
                        .name(product.getDemoCategory().getName())
                        .state(product.getDemoCategory().isState())
                        .build())
                .build();
    }

    public List<ProductResponseDTO> mapperListEntityToDto(List<ProductEntity> demoProduct) {
        return demoProduct
                .stream()
                .map(this::assignToDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDTO> mapperEntityToDto(Optional<ProductEntity> product) {
        return product.map(this::assignToDto);
    }
}
