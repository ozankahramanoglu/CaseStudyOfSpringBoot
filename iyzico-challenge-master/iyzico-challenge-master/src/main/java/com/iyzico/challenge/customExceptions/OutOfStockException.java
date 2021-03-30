package com.iyzico.challenge.customExceptions;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(Long id) {
        super("Product's stock is depleted " + id);
    }
}
