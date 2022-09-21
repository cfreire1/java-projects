package com.example.api.persistence;

import com.example.api.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<ProductEntity> getAll();
    Optional<ProductEntity> findBy(int id);
    Optional<ProductEntity> save(ProductEntity product);
    void delete(int id);
}
