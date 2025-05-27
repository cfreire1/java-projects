package com.example.javaspringbootrestusers.infrastructure.persistence.mapper;

import com.example.javaspringbootrestusers.domain.model.Users;
import com.example.javaspringbootrestusers.infrastructure.persistence.entity.UsersEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryMapper {
    public Users toDomain (UsersEntity usersEntity){
        return Users.builder()
                .name(usersEntity.getName())
                .email(usersEntity.getEmail())
                .password(usersEntity.getPassword())
                .idUser(usersEntity.getIdUser())
                .created(usersEntity.getCreated())
                .modified(usersEntity.getModified())
                .lastLogin(usersEntity.getLastLogin())
                .tokenJwt(usersEntity.getTokenJwt())
                .isActive(usersEntity.isActive())
                .build();
    }

    public UsersEntity toEntity(Users users){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setName(users.getName());
        usersEntity.setEmail(users.getEmail());
        usersEntity.setPassword(users.getPassword());
        usersEntity.setIdUser(users.getIdUser());
        usersEntity.setCreated(users.getCreated());
        usersEntity.setModified(users.getModified());
        usersEntity.setLastLogin(users.getLastLogin());
        usersEntity.setTokenJwt(users.getTokenJwt());
        usersEntity.setActive(users.isActive());

        return usersEntity;
    }
}
