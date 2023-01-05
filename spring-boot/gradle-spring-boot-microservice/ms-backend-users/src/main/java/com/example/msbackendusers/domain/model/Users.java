package com.example.msbackendusers.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    private UUID idUser;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String tokenJwt;
    private boolean isActive;
    private List<Phone> phones;
}
