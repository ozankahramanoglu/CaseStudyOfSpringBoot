package com.iyzico.challenge.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "Payment")
public class Payment {

    public enum Status{
        InProgress,
        Completed,
        Error;
    }

    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal price;
    private String bankResponse;
    @Transient
    private Status status;
    @Transient
    private List<Product> products;
    @Transient
    private List<Integer> productQuantity;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBankResponse() {
        return bankResponse;
    }

    public void setBankResponse(String bankResponse) {
        this.bankResponse = bankResponse;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Integer> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(List<Integer> productQuantity) {
        this.productQuantity = productQuantity;
    }
}
