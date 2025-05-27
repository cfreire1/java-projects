package com.example.springbootresthexagonalarchitecture.infrastructure.adapters.persistence.crud;

import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductCrudJpaRepository extends CrudRepository<ProductEntity,Integer> {
}
