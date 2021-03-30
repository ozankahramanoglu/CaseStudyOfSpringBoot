package com.iyzico.challenge.controllers;


import com.iyzico.challenge.entity.Payment;
import com.iyzico.challenge.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @RequestMapping("/buyProducts")
    public HttpStatus buyProducts(@RequestBody Payment payment){
        return buyProducts(payment);
    }
}
