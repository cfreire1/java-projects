package com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.mapper;

import com.example.springbootresthexagonalarchitecture.domain.model.Product;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.dto.ProductDTO;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.dto.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductControllerMapper {
    @Mappings({
            // Domain / Dto
//            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "price",target = "price"),
            @Mapping(source = "state",target = "state")
    })
    Product toDomain(ProductRequest productRequest);

    List<ProductDTO> toListDomain(List<Product> products);
}
