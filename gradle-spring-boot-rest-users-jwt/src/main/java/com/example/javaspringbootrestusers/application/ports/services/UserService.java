package com.example.javaspringbootrestusers.application.ports.services;

import com.example.javaspringbootrestusers.domain.model.Users;

public interface UserService {

    Users createUser (Users users,String token);

}
