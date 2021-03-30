package com.iyzico.challenge.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OutOfStockAdvice {
    @ResponseBody
    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String outOfStockHandler(OutOfStockAdvice ex){
        return ex.toString();
    }
}
