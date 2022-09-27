package com.example.javaspringbootrestusers.infrastructure.controller.dto;

import lombok.*;

import java.util.List;

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
