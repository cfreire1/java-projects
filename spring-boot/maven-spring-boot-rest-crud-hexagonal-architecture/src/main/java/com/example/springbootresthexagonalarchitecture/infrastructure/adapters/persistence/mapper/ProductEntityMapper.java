package com.example.springbootresthexagonalarchitecture.infrastructure.adapters.persistence.mapper;

import com.example.springbootresthexagonalarchitecture.domain.model.Product;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    @Mappings({
            // Domain / entity
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "price",target = "price"),
            @Mapping(source = "state",target = "state")
    })
    Product toDomain(ProductEntity productEntity);

    @Mappings({
            // entity / Domain
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "price",target = "price"),
            @Mapping(source = "state",target = "state")
    })
    ProductEntity toEntity(Product product);

    List<Product> toListDomain(List<ProductEntity> productEntity);

}
