package com.example.api.persistence;


import com.example.api.persistence.dao.crud.ProductCrudRepository;
import com.example.api.persistence.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    /**
     * Retorna todos los productos
     * @return List<ProductEntity>
     */
    @Override
    public List<ProductEntity> getAll() {
        return (List<ProductEntity>) productCrudRepository.findAll();
    }

    /**
     * Busqueda de producto
     * @param id Registro de BD
     * @return Optional<ProductEntity>
     */
    @Override
    public Optional<ProductEntity> findBy(int id) {
        return productCrudRepository.findById(id);
    }

    /**
     * Guarda un producto
     * @param product Producto con atributos
     * @return Optional<ProductEntity>
     */
    @Override
    public Optional<ProductEntity> save(ProductEntity product) {
        return Optional.of(productCrudRepository.save(product));
    }

    /**
     * Borra un producto seleccionado
     * @param id Registro de BD
     */
    @Override
    public void delete(int id) {
        productCrudRepository.deleteById(id);
    }

}
