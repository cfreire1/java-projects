package com.example.msbackenddataaccesslayerusers.infrastructure.persistence.jpa;

import com.example.msbackenddataaccesslayerusers.infrastructure.persistence.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserCrudRepository extends CrudRepository<Users, UUID> {

    @Query(value = "SELECT COUNT(email) FROM USERS  WHERE email= ?",nativeQuery = true)
    Integer existEmail (String email);
}
