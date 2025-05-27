package com.example.springbootrestbasic.web.controller;

import com.example.springbootrestbasic.domain.dto.CategoryRequestDTO;
import com.example.springbootrestbasic.domain.dto.CategoryResponseDTO;
import com.example.springbootrestbasic.domain.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryResponseDTO>> getAll(){
        return new ResponseEntity<>(categoryServices.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-find/{id}")
    public ResponseEntity<CategoryResponseDTO> findBy(@PathVariable(value = "id") int id){
        return categoryServices.findBy(id)
                .map(categoryResponseDTO -> new ResponseEntity<>(categoryResponseDTO,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryRequestDTO> save(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return categoryServices.save(categoryRequestDTO)
                .map(categoryRequestDTO1 -> new ResponseEntity<>(categoryRequestDTO1,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryRequestDTO> update(@RequestBody CategoryRequestDTO categoryRequestDTO,
                                               @PathVariable(value = "id") int id){
        return categoryServices.update(categoryRequestDTO,id)
                .map(categoryRequestDTO1 -> new ResponseEntity<>(categoryRequestDTO1,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") int id){
        return Optional.of(new ResponseEntity(categoryServices.delete(id),HttpStatus.OK))
                .orElse(new ResponseEntity<>(false,HttpStatus.NOT_FOUND));
    }

}
