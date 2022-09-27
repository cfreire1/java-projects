package com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private boolean state;
}
