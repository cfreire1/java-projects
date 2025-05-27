package com.example.springbootrestbasic.domain.services;

import com.example.springbootrestbasic.domain.dto.CategoryResponseDTO;
import com.example.springbootrestbasic.domain.dto.CategoryRequestDTO;
import com.example.springbootrestbasic.domain.dummy.DataDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service//Logica de negocio
public class CategoryServicesImpl implements CategoryServices{

    @Autowired
    private DataDummy dataDummy;

    @Override
    public List<CategoryResponseDTO> getAll() {
        return dataDummy.dummyCategoryResponseDTOList();
    }

    @Override
    public Optional<CategoryResponseDTO> findBy(int id) {
        return dataDummy.dummyCategoryResponseDTOOptional();
    }

    @Override
    public Optional<CategoryRequestDTO> save(CategoryRequestDTO categoryRequestDTO) {
        return dataDummy.dummyCategoryRequestDTOOptional();
    }

    @Override
    public Optional<CategoryRequestDTO> update(CategoryRequestDTO categoryRequestDTO, int id) {
        return dataDummy.dummyCategoryRequestDTOOptional();
    }

    @Override
    public boolean delete(int id) {
        return true;
    }
}
