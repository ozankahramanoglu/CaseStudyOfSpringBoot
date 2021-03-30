package com.iyzico.challenge.customExceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("Product not found " + id);
    }
}
