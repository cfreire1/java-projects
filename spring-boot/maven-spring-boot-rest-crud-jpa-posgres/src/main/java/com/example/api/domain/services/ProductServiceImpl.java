package com.example.api.domain.services;

import com.example.api.domain.dto.ProductRequestDTO;
import com.example.api.domain.dto.ProductResponseDTO;
import com.example.api.domain.mapper.ProductRequestMapper;
import com.example.api.domain.mapper.ProductResponseMapper;
import com.example.api.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductResponseMapper productResponseMapper;

    @Autowired
    private ProductRequestMapper productRequestMapper;

    @Override
    public List<ProductResponseDTO> getAll() {
        return productResponseMapper.mapperListEntityToDto(productRepository.getAll());
    }

    @Override
    public Optional<ProductResponseDTO> findBy(int id) {
        return productResponseMapper.mapperEntityToDto(productRepository.findBy(id));
    }

    @Override
    public Optional<ProductRequestDTO> save(ProductRequestDTO productRequestDTO) {
        return productRequestMapper
                .mapperEntityToDto(productRepository
                        .save(productRequestMapper.mapperDtoToEntity(productRequestDTO)));
    }

    @Override
    public Optional<ProductRequestDTO> update(ProductRequestDTO productRequestDTO, int id) {
        return productRequestMapper
                .mapperEntityToDto(productRepository
                        .findBy(id)
                            .flatMap(productEntity -> {
                                productEntity = productRequestMapper.mapperDtoToEntity(productEntity,productRequestDTO);
                                return productRepository.save(productEntity);
                            }));
    }


    @Override
    public boolean delete(int id) {
        if (findBy(id).isPresent()){
            productRepository.delete(id);
            return true;
        }
       return false;

    }

}
