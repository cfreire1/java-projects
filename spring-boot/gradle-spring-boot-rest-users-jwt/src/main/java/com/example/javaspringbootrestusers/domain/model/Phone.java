package com.example.javaspringbootrestusers.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {
    private long number;
    private UUID idUser;
    private String cityCode;
    private String countryCode;

}
