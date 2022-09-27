package com.example.api.domain.services;

import com.example.api.domain.dto.CategoryRequestDTO;
import com.example.api.domain.dto.CategoryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {
    List<CategoryResponseDTO> getAll();
    Optional<CategoryResponseDTO> findBy(int id);
    Optional<CategoryRequestDTO> save(CategoryRequestDTO categoryRequestDTO);
    Optional<CategoryRequestDTO> update(CategoryRequestDTO categoryRequestDTO,int id);
    boolean delete(int id);
}
