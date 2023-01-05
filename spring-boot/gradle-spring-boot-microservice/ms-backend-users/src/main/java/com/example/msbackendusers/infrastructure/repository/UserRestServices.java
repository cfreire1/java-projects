package com.example.msbackendusers.infrastructure.repository;

import com.example.msbackendusers.domain.model.Phone;
import com.example.msbackendusers.domain.model.Users;

public interface UserRestServices {

    Users saveUser(Users users);
    boolean existEmail(String email);
    Phone savePhone (Phone phoneEntity);
    boolean existPhone(long nphone);
}
