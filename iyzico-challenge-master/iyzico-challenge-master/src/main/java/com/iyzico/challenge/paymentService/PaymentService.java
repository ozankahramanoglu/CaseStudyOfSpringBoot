package com.iyzico.challenge.paymentService;

import com.iyzico.challenge.entity.Payment;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    public abstract HttpStatus buyProducts (Payment payment);
}
