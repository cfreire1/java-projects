package com.example.api.persistence.dao.crud;

import com.example.api.persistence.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;


public interface CategoryCrudRepository extends CrudRepository<CategoryEntity,Integer> {
}
