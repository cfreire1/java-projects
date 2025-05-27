package com.example.springbootrestbasic.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsernameDTO {
    private String username;
    private String password;
    private String role;
    private boolean state;
}
