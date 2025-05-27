package com.example.msbackendusers.infrastructure.controller.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersRequestDTO {
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;

}
