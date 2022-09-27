package com.example.api.domain.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDTO {
    private Integer id;
    private String name;
    private boolean state;
}
