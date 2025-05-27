package com.example.javaspringbootrestusers.infrastructure.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthToken {
    private String tokenJwt;
}
