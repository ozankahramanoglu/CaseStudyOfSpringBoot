package com.iyzico.challenge.service;

import com.iyzico.challenge.productService.ProductService;
import com.iyzico.challenge.entity.Payment;
import com.iyzico.challenge.repository.PaymentRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import com.zaxxer.hikari.pool.HikariPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.Stack;

@Service
public class IyzicoPaymentService {

    private Logger logger = LoggerFactory.getLogger(IyzicoPaymentService.class);

    private BankService bankService;
    private PaymentRepository paymentRepository;
    private ProductService productService;

    public IyzicoPaymentService(BankService bankService, PaymentRepository paymentRepository) {
        this.bankService = bankService;
        this.paymentRepository = paymentRepository;
    }
    public void pay(BigDecimal price) {
        //pay with bank
        BankPaymentRequest request = new BankPaymentRequest();
        request.setPrice(price);
        BankPaymentResponse response = bankService.pay(request);

        //insert records
        Payment payment = new Payment();
        payment.setBankResponse(response.getResultCode());
        payment.setPrice(price);
        Stack<Payment> stack = new Stack<>();
        stack.push(payment);
        //paymentRepository.save(payment);

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        config.setJdbcUrl("jdbc:hsqldb:hsql://localhost/testdb");
        config.setUsername("SA");
        config.setPoolName("my db pool");
        config.setMaximumPoolSize(2);
        HikariDataSource ds = new HikariDataSource(config);

        while(!stack.isEmpty()){
            HikariPool hikariPool = (HikariPool) new DirectFieldAccessor(ds).getPropertyValue("pool");
            if(hikariPool.getIdleConnections()>1){
                paymentRepository.save(stack.pop());
                logger.info("Payment saved successfully!");
            }
        }
    }
}
