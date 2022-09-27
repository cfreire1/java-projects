package com.example.javaspringbootrestusers.infrastructure.persistence;

import com.example.javaspringbootrestusers.application.ports.persistence.UserRepository;
import com.example.javaspringbootrestusers.domain.model.Users;
import com.example.javaspringbootrestusers.infrastructure.persistence.entity.UsersEntity;
import com.example.javaspringbootrestusers.infrastructure.persistence.jpa.UserCrudRepository;
import com.example.javaspringbootrestusers.infrastructure.persistence.mapper.UserRepositoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserRepositoryMapper mapper;



    @Override
    public Users saveUser(Users users) {
        UsersEntity usersEntity = mapper.toEntity(users);
        return mapper.toDomain(userCrudRepository.save(usersEntity));
    }

    @Override
    public boolean existEmail(String email) {
        if (userCrudRepository.existEmail(email) != 0){
            return true;
        }
        return false;
    }
}
