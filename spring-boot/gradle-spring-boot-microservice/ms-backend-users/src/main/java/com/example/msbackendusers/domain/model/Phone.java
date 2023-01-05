package com.example.msbackendusers.domain.model;

import java.util.UUID;
import lombok.*;
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
