package com.example.springbootrestbasic.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private String jwt;
}
