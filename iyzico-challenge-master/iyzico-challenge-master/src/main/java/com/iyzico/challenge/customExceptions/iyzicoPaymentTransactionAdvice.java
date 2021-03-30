package com.iyzico.challenge.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class iyzicoPaymentTransactionAdvice {

    @ResponseBody
    @ExceptionHandler(iyzicoPaymentTransactionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String iyzicoPaymentTransactionHandler(iyzicoPaymentTransactionAdvice ex){
        return ex.toString();
    }
}
