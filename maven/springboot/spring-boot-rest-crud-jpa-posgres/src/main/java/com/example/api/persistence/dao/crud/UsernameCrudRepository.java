package com.example.api.persistence.dao.crud;

import com.example.api.persistence.entity.UsernameEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsernameCrudRepository extends CrudRepository<UsernameEntity, String> {
}
