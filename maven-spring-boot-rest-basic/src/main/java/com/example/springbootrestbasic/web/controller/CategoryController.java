package com.example.springbootrestbasic.web.controller;

import com.example.springbootrestbasic.domain.dto.CategoryRequestDTO;
import com.example.springbootrestbasic.domain.dto.CategoryResponseDTO;
import com.example.springbootrestbasic.domain.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping("/get-all")
    public List<CategoryResponseDTO> getAll(){
        return categoryServices.getAll();
    }

    @GetMapping("/get-find/{id}")
    public Optional<CategoryResponseDTO> findBy(@PathVariable(value = "id") int id){
        return categoryServices.findBy(id);
    }

    @PostMapping("/save")
    public Optional<CategoryRequestDTO> save(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return categoryServices.save(categoryRequestDTO);
    }

    @PutMapping("/update/{id}")
    public Optional<CategoryRequestDTO> update(@RequestBody CategoryRequestDTO categoryRequestDTO,
                                               @PathVariable(value = "id") int id){
        return categoryServices.update(categoryRequestDTO,id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable(value = "id") int id){
        return categoryServices.delete(id);
    }

}
