package com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller;

import com.example.springbootresthexagonalarchitecture.application.ports.service.ProductService;
import com.example.springbootresthexagonalarchitecture.domain.model.Product;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.dto.MsgResponse;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.dto.ProductRequest;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.dto.ProductResponse;
import com.example.springbootresthexagonalarchitecture.infrastructure.adapters.controller.mapper.ProductControllerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

/**
 * [1] El adaptador(ProductController) usa el puerto(ProductService), sin conocer la implementación.
 * [2] Llama al mapper y transforma los datos para que coincidan con el dominio y el exterior.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductControllerMapper productControllerMapper;

    @GetMapping("/get-all")
    public ResponseEntity<ProductResponse> getAllProduct(){
        return new ResponseEntity<>(
                ProductResponse.builder()
                        .msgResponse(MsgResponse.builder()
                                .message("Obtencion de Todas los productos")
                                .status(HttpStatus.OK)
                                .build())
                        .productDtoResponseList(productControllerMapper.toListDomain(productService.getAllProducts()))
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("/find/{idProduct}")
    public ResponseEntity<ProductResponse> findIdProduct(@PathVariable("idProduct") int idProduct){
        Product product = productService.findByIdProduct(idProduct);

        return Optional.ofNullable(product)
                .map(product1 ->
                        new ResponseEntity<>(
                                ProductResponse.builder()
                                        .msgResponse(MsgResponse.builder()
                                                .message("Resultado de busqueda")
                                                .status(HttpStatus.OK)
                                                .build())
                                        .productDtoResponseList(productControllerMapper.toListDomain(Arrays.asList(product1)))
                                        .build()
                                , HttpStatus.OK)
                        )
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/save")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest productRequest){
        Product product = productService.saveProduct(productControllerMapper.toDomain(productRequest));
        return new ResponseEntity(
                ProductResponse.builder()
                        .msgResponse(MsgResponse.builder()
                                .message("Se ha creado el producto")
                                .status(HttpStatus.CREATED)
                                .build())
                        .productDtoResponseList(productControllerMapper.toListDomain(Arrays.asList(product)))
                        .build()
                ,HttpStatus.CREATED);
    }

    @PutMapping("/update/{idProduct}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable("idProduct") int idProduct){
        Product product = productService.updateProduct(productControllerMapper.toDomain(productRequest),idProduct);

        return Optional.ofNullable(product)
                .map(product1 ->
                        new ResponseEntity<>(
                                ProductResponse.builder()
                                        .msgResponse(MsgResponse.builder()
                                                .message("Se a ha modificado el producto")
                                                .status(HttpStatus.OK)
                                                .build())
                                        .productDtoResponseList(productControllerMapper.toListDomain(Arrays.asList(product1)))
                                        .build()
                                ,HttpStatus.OK)
                )
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{idProduct}")
    public ResponseEntity<MsgResponse> deleteProduct(@PathVariable("idProduct") int idProduct){
        if(productService.deleteProduct(idProduct)){
            return new ResponseEntity<>(
                    MsgResponse.builder()
                            .message("Se a eliminado el registro")
                            .status(HttpStatus.OK)
                            .build()
                    ,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
