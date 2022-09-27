package com.example.api.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private Integer id;
    private Integer idCategory;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime createAt;
    private boolean state;

    private CategoryResponseDTO demoCategoryDTO;
}
