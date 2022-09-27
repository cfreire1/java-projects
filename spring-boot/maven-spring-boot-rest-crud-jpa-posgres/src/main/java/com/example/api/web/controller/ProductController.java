package com.example.api.web.controller;

import com.example.api.domain.dto.ProductRequestDTO;
import com.example.api.domain.dto.ProductResponseDTO;
import com.example.api.domain.services.ProductService;
import com.example.api.web.config.handler.exceptions.ApiNotFoundException;
import com.example.api.web.config.handler.exceptions.ApiNotModifiedException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * Bucar todos los productos
     * @return
     */
    @GetMapping("/all")
    @ApiOperation("Get all Product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<List<ProductResponseDTO>> getAll (){
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }

    /**
     * Busqueda de Producto
     *
     * @param id
     * @return
     */
    @GetMapping("/find/{id_product}")
    @ApiOperation("Find product for id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<ProductResponseDTO> findBy(@ApiParam(value = "Id for Product", required = true, example = "1")
                                                         @PathVariable("id_product") int id){
        return productService.findBy(id)
                .map(productResponseDTO -> new ResponseEntity<>(productResponseDTO,HttpStatus.OK))
                .orElseThrow(() -> new ApiNotModifiedException("No se encuentra la categoria"));
    }

    /**
     * Agregar producto
     * @param productRequestDTO
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("Save product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error insert for Product")
    })
    public ResponseEntity<ProductRequestDTO> save(@RequestBody ProductRequestDTO productRequestDTO){
        return productService.save(productRequestDTO)
                .map(productRequestDTO1 -> new ResponseEntity<>(productRequestDTO1,HttpStatus.CREATED))
                .orElseThrow(() -> new ApiNotFoundException("No se inserto el registro de producto"));
    }

    /**
     * Actualiza un producto
     * @param productRequestDTO
     * @return
     */
    @PutMapping("/update/{id}")
    @ApiOperation("Update product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error update for Product")
    })
    public ResponseEntity<ProductRequestDTO> update(@RequestBody ProductRequestDTO productRequestDTO,
                                               @PathVariable("id") int id){
        return productService.update(productRequestDTO,id)
                .map(productRequestDTO1 -> new ResponseEntity<>(productRequestDTO1,HttpStatus.OK))
                .orElseThrow(() -> new ApiNotModifiedException("No se pudo modificar el producto"));
    }

    /**
     * Borrar producto
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id_product}")
    @ApiOperation("Delete product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Error delete  Product")
    })
    public ResponseEntity delete(@PathVariable("id_product") int id){
        if (productService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new ApiNotModifiedException("No se pudo eliminar el producto");
        }

    }
}
