package com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private MsgResponse msgResponse;
    private List<ProductDTO> productDtoResponseList;
}
