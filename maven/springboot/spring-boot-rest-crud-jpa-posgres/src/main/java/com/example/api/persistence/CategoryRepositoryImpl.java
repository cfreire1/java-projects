package com.example.api.persistence;

import com.example.api.persistence.dao.crud.CategoryCrudRepository;
import com.example.api.persistence.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    /**
     * Retorna todos los categoria
     * @return List<ProductEntity>
     */
    @Override
    public List<CategoryEntity> getAll() {
        return (List<CategoryEntity>) categoryCrudRepository.findAll();
    }

    /**
     * Busqueda de categoria
     * @param id Registro de BD
     * @return Optional<CategoryEntity>
     */
    @Override
    public Optional<CategoryEntity> findBy(int id) {
        return categoryCrudRepository.findById(id);
    }

    /**
     * Guarda un categoria
     * @param category categoria con atributos
     * @return Optional<CategoryEntity>
     */
    @Override
    public Optional<CategoryEntity> save(CategoryEntity category) {
        return Optional.of(categoryCrudRepository.save(category));
    }

    /**
     * Borra un categoria seleccionado
     * @param id Registro de BD
     */
    @Override
    public void delete(int id) {
        categoryCrudRepository.deleteById(id);
    }

}
