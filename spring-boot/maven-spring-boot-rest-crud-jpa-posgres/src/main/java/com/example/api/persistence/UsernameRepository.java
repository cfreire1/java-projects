package com.example.api.persistence;

import com.example.api.persistence.entity.UsernameEntity;

import java.util.Optional;

public interface UsernameRepository {
    Optional<UsernameEntity> findUsername(String UserName);

}
