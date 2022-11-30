package com.example.javaspringbootrestusers.infrastructure.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDTO {
    private String number;
    private String citycode;
    private String contrycode;
}
