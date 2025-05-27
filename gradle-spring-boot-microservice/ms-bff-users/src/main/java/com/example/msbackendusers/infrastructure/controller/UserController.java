package com.example.msbackendusers.infrastructure.controller;

import com.example.msbackendusers.application.ports.services.UserService;
import com.example.msbackendusers.infrastructure.controller.dto.UsersRequestDTO;
import com.example.msbackendusers.infrastructure.controller.dto.UsersResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    public UserController(@Autowired(required = false) UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<UsersResponseDTO> createUser(@RequestHeader("Authorization") String token, @RequestBody UsersRequestDTO usersRequestDTO){
        return new ResponseEntity<>(userService.createUser(usersRequestDTO,token), HttpStatus.OK);
    }
}
