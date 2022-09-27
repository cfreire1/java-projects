package com.example.springbootrestbasic.web.controller;

import com.example.springbootrestbasic.domain.dto.CategoryRequestDTO;
import com.example.springbootrestbasic.domain.dto.CategoryResponseDTO;
import com.example.springbootrestbasic.domain.services.CategoryServices;
import com.example.springbootrestbasic.web.config.handler.exceptions.ApiNotFoundException;
import com.example.springbootrestbasic.web.config.handler.exceptions.ApiNotModifiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller clon de 'CategoryController' y configurado para lanzar solo el error
 */
@RestController
@RequestMapping("/category-error")
public class CategoryErrorController {

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryResponseDTO>> getAll(){
        throw new ApiNotFoundException("Sin categorias");
    }

    @GetMapping("/get-find/{id}")
    public ResponseEntity<CategoryResponseDTO> findBy(@PathVariable(value = "id") int id){
        throw new ApiNotFoundException("Sin categorias");
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryRequestDTO> save(@RequestBody CategoryRequestDTO categoryRequestDTO){
        throw new ApiNotModifiedException("No se puede guardar categoria");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryRequestDTO> update(@RequestBody CategoryRequestDTO categoryRequestDTO,
                                               @PathVariable(value = "id") int id){
        throw new ApiNotModifiedException("No se puede actualizar categoria");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") int id){
        throw new ApiNotFoundException("La categoria no se puede eliminar por que no existe");
    }

}
