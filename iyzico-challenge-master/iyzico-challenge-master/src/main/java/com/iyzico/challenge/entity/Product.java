package com.iyzico.challenge.entity;

import java.util.Objects;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int remainingStock;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id;  }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public int getRemainingStock() { return remainingStock; }

    public void setRemainingStock(int remainingStock) { this.remainingStock = remainingStock; }

    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (! (o instanceof Product)){
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(this.id,product.id) &&
                Objects.equals(this.name,product.name) &&
                Objects.equals(this.description,product.description) &&
                Objects.equals(this.price,product.price) &&
                Objects.equals(this.remainingStock,product.remainingStock);
    }
    public int hashCode(){
        return Objects.hash(this.id,this.name,this.description,this.price,this.remainingStock);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", remainingStock=" + remainingStock +
                '}';
    }
}
