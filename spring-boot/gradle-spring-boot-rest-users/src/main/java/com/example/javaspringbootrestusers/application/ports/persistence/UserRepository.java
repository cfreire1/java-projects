package com.example.javaspringbootrestusers.application.ports.persistence;

import com.example.javaspringbootrestusers.domain.model.Users;


public interface UserRepository {
    Users saveUser(Users users);
    boolean existEmail(String email);
}
