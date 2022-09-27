package com.example.springbootresthexagonalarchitecture.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    private boolean state;
}
