package com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private BigDecimal price;
    private boolean state;

}
