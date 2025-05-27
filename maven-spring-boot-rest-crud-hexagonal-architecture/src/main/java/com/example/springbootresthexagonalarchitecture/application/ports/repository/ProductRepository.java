package com.example.springbootresthexagonalarchitecture.application.ports.repository;

import com.example.springbootresthexagonalarchitecture.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAllProducts();
    Product findByIdProduct(int idProduct);
    Product saveProduct(Product product);
    Product updateProduct(Product product, int idProduct);
    boolean deleteProduct(int idProduct);
}
