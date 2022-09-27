package com.example.api.persistence;

import com.example.api.persistence.dao.crud.UsernameCrudRepository;
import com.example.api.persistence.entity.UsernameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsernameRepositoryImpl implements UsernameRepository{

    @Autowired
    private UsernameCrudRepository usernameCrudRepository;

    @Override
    public Optional<UsernameEntity> findUsername(String UserName) {
        return usernameCrudRepository.findById(UserName);
    }
}
