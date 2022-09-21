package com.example.api.web.controller;

import com.example.api.domain.dto.CategoryRequestDTO;
import com.example.api.domain.dto.CategoryResponseDTO;
import com.example.api.domain.services.CategoryServices;
import com.example.api.web.config.handler.exceptions.ApiNotFoundException;
import com.example.api.web.config.handler.exceptions.ApiNotModifiedException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//esta clase sera una api rest
@RequestMapping("/cart/category")//que path aceptar las peticiones que hagamos
@Api( tags = "category")
public class CategoryController {
    @Autowired
    CategoryServices categoryServices;

    /**
     * Bucar todos las categorias
     * @return
     */
    @GetMapping("/all")
    @ApiOperation("Get all Category")
    @ApiResponses({//DOC swagger
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<List<CategoryResponseDTO>> getAll (){

        return new ResponseEntity<>(categoryServices.getAll(), HttpStatus.OK);

    }

    /**
     * Busqueda de categoria
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    @ApiOperation("Find category for id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<CategoryResponseDTO> findBy(@ApiParam(value = "Id for category", required = true, example = "1")
                                                          @PathVariable("id") int id){
        return categoryServices.findBy(id)
                .map(categoryResponseDTO -> new ResponseEntity<>(categoryResponseDTO,HttpStatus.OK))
                .orElseThrow(() -> new ApiNotModifiedException("No se encuentra la categoria"));
    }

    /**
     * Agregar categoria
     * @param categoryRequestDTO
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("Save Cetegory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error insert for Category")
    })
    public ResponseEntity<CategoryRequestDTO> save(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return categoryServices.save(categoryRequestDTO)
                .map(categoryRequestDTO1 -> new ResponseEntity<>(categoryRequestDTO1,HttpStatus.CREATED))
                .orElseThrow(() -> new ApiNotFoundException("No se inserto el registro de categoria"));
    }

    /**
     * Actualiza una categoria
     * @param categoryRequestDTO
     * @return
     */
    @PutMapping("/update/{id}")
    @ApiOperation("Update Cetegory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 304, message = "Error update for Category")
    })
    public ResponseEntity<CategoryRequestDTO> update(@RequestBody CategoryRequestDTO categoryRequestDTO,
                          @PathVariable("id") int id){
        return categoryServices.update(categoryRequestDTO,id)
                .map(categoryRequestDTO1 -> new ResponseEntity<>(categoryRequestDTO1,HttpStatus.OK))
                .orElseThrow(() -> new ApiNotModifiedException("No se pudo modificar la categoria"));
    }

    /**
     * Borrar categoria
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("delete Cetegory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error delete for Category")
    })
    public ResponseEntity delete(@PathVariable("id") int id){
        if (categoryServices.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new ApiNotModifiedException("No se pudo eliminar la categoria");
        }
    }
}
