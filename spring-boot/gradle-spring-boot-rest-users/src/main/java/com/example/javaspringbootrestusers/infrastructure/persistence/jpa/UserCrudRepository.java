package com.example.javaspringbootrestusers.infrastructure.persistence.jpa;

import com.example.javaspringbootrestusers.infrastructure.persistence.entity.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserCrudRepository extends CrudRepository<UsersEntity, UUID> {

    @Query(value = "SELECT COUNT(email) FROM USERS  WHERE email= ?",nativeQuery = true)
    Integer existEmail (String email);
}
