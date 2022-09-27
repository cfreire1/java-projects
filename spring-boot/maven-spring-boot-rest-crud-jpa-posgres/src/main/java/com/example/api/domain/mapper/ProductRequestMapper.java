package com.example.api.domain.mapper;

import com.example.api.domain.dto.ProductRequestDTO;
import com.example.api.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRequestMapper {

    public ProductEntity mapperDtoToEntity(ProductRequestDTO productDTO) {
        return ProductEntity.builder()
                .idCategory(productDTO.getIdCategory())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .createAt(productDTO.getCreateAt())
                .state(productDTO.isState())
                .build();
    }

    public ProductEntity mapperDtoToEntity(ProductEntity productEntity, ProductRequestDTO productDTO) {

        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setStock(productDTO.getStock());
        productEntity.setCreateAt(productDTO.getCreateAt());
        productEntity.setState(productDTO.isState());

        return productEntity;
    }

    public Optional<ProductRequestDTO> mapperEntityToDto(Optional<ProductEntity> product) {
        return product.map(product1 ->
                ProductRequestDTO.builder()
                        .id(Optional.ofNullable(product1.getId()).orElse(null))
                        .idCategory(product1.getIdCategory())
                        .name(product1.getName())
                        .price(product1.getPrice())
                        .stock(product1.getStock())
                        .createAt(product1.getCreateAt())
                        .state(product1.isState())
                        .build());
    }

}
