package com.example.api.domain.services;

import com.example.api.domain.dto.ProductRequestDTO;
import com.example.api.domain.dto.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDTO> getAll();
    Optional<ProductResponseDTO> findBy(int id);
    Optional<ProductRequestDTO> save(ProductRequestDTO productRequestDTO);

    Optional<ProductRequestDTO> update(ProductRequestDTO productRequestDTO,int id);
    boolean delete(int id);
}
