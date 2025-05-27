package com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MsgResponse {
    private String message;
    private HttpStatus status;
}
