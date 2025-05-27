package com.example.msbackendusers.application.ports.services;

import com.example.msbackendusers.infrastructure.controller.dto.UsersRequestDTO;
import com.example.msbackendusers.infrastructure.controller.dto.UsersResponseDTO;

public interface UserService {

    UsersResponseDTO createUser (UsersRequestDTO users, String token);

}
