package com.example.msbackenddataaccesslayerusers.application.ports.persistence;

import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.entity.Users;


public interface UserRepository {
    Users saveUser(Users users);
    boolean existEmail(String email);
}
