package com.example.springbootresthexagonalarchitecture.infrastructure.adapters.persistence;

import com.example.springbootresthexagonalarchitecture.application.ports.repository.ProductRepository;
import com.example.springbootresthexagonalarchitecture.domain.model.Product;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.persistence.crud.ProductCrudJpaRepository;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.persistence.entity.ProductEntity;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.persistence.mapper.ProductEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductCrudJpaRepository productCrudJpaRepository;

    @Autowired
    private ProductEntityMapper productEntityMapper;

    @Override
    public List<Product> getAllProducts() {
        return productEntityMapper
                .toListDomain((List<ProductEntity>) productCrudJpaRepository.findAll());
    }

    @Override
    public Product findByIdProduct(int idProduct) {
        Optional<ProductEntity> opProductEntity  = productCrudJpaRepository.findById(idProduct);
        return productEntityMapper.toDomain(opProductEntity.orElse(null));
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity =  productEntityMapper.toEntity(product);
        return productEntityMapper.toDomain(productCrudJpaRepository.save(productEntity));
    }

    @Override
    public Product updateProduct(Product product, int idProduct) {
        if (this.findByIdProduct(idProduct) == null){
           return null;
        }
        product.setId(idProduct);
        return this.saveProduct(product);
    }

    @Override
    public boolean deleteProduct(int idProduct) {
        if (this.findByIdProduct(idProduct) == null){
            return false;
        }
        productCrudJpaRepository.deleteById(idProduct);
        return true;
    }
}
