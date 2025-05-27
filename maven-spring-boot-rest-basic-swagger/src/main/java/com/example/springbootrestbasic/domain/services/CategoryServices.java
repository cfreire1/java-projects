package com.example.springbootrestbasic.domain.services;

import com.example.springbootrestbasic.domain.dto.CategoryRequestDTO;
import com.example.springbootrestbasic.domain.dto.CategoryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {
    List<CategoryResponseDTO> getAll();
    Optional<CategoryResponseDTO> findBy(int id);
    Optional<CategoryRequestDTO> save(CategoryRequestDTO categoryRequestDTO);
    Optional<CategoryRequestDTO> update(CategoryRequestDTO categoryRequestDTO,int id);
    boolean delete(int id);
}
