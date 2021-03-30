package com.iyzico.challenge.controllers;

import com.iyzico.challenge.productService.ProductService;
import com.iyzico.challenge.customExceptions.ProductNotFoundException;
import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;

@RestController
public class ProductController {


    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    ProductService productService;

    @GetMapping("/Product")
    Collection<Product> getall(){
        return productRepository.findAll();
    }

    @PostMapping("/Product")
    Product newProduct(@RequestBody Product newProduct){
        return productRepository.save(newProduct);
    }

    @GetMapping("/Product/{ids}")
    List<Product> getbyIds(@PathVariable List<Long> ids){
        return productRepository.findAllById(ids);
    }

    @PutMapping("/Product/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id){
        productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException(id));
        return productRepository.findById(id)
                .map(product -> {
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setName(newProduct.getName());
                    product.setRemainingStock(newProduct.getRemainingStock());
                    return productRepository.save(product);
                })
                .orElseGet(()-> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    @DeleteMapping("/Product/{id}")
    HttpStatus deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
