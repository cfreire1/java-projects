package com.example.api.persistence.dao.crud;

import com.example.api.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProductCrudRepository extends CrudRepository<ProductEntity,Integer> {
}
