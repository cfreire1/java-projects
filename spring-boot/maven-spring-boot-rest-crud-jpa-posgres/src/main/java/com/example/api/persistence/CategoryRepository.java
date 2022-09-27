package com.example.api.persistence;

import com.example.api.persistence.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<CategoryEntity> getAll();
    Optional<CategoryEntity> findBy(int id);
    Optional<CategoryEntity> save(CategoryEntity category);
    void delete(int id);
}
