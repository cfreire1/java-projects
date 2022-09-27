package com.example.springbootresthexagonalarchitecture.domain;

import com.example.springbootresthexagonalarchitecture.application.ports.repository.ProductRepository;
import com.example.springbootresthexagonalarchitecture.application.ports.service.ProductService;
import com.example.springbootresthexagonalarchitecture.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Aqui estara la logica de negocio, segun aplique
 */
@Service
public class ProductServiceImpl implements ProductService  {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product findByIdProduct(int idProduct) {
        return productRepository.findByIdProduct(idProduct);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product updateProduct(Product product, int idProduct) {
        return productRepository.updateProduct(product,idProduct);
    }

    @Override
    public boolean deleteProduct(int idProduct) {
        return productRepository.deleteProduct(idProduct);
    }
}
