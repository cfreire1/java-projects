package com.example.msbackenddataaccesslayerusers.infrastructure.persistence;

import com.example.msbackenddataaccesslayerusers.application.ports.persistence.UserRepository;
import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.entity.Users;
import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.jpa.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Override
    public Users saveUser(Users users) {
        return userCrudRepository.save(users);
    }

    @Override
    public boolean existEmail(String email) {
        if (userCrudRepository.existEmail(email) != 0){
            return true;
        }
        return false;
    }
}
