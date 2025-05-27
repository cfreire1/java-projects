package com.example.jwttokenacces.infrastructure.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthToken {
    private String tokenJwt;
}
