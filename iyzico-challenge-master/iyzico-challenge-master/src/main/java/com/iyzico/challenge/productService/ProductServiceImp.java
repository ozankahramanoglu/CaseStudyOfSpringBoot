package com.iyzico.challenge.productService;

import com.iyzico.challenge.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImp implements ProductService{

    @Override
    public Collection<Product> getAll() {
        return getAll();
    }

    @Override
    public Product newProduct(Product newProduct) {
        return newProduct(newProduct);
    }

    @Override
    public Collection<Product> getByIds(List<Long> ids) {
        return getByIds(ids);
    }

    public Product getById(Long id){
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        Product product = getByIds(ids).iterator().next();
        return product;
    }

    @Override
    public Product replaceProduct(Product newProduct, Long id) {
        return replaceProduct(newProduct,id);
    }

    @Override
    public HttpStatus deleteProduct(Long id) {
        return deleteProduct(id);
    }

    public int remainingStock(Long id){
        Product product = getById(id);
        return product.getRemainingStock();
    }

    public HttpStatus blockStock(Long id, Integer quantity) {
        Product productblocked = getById(id);
        productblocked.setRemainingStock(productblocked.getRemainingStock() - quantity);
        replaceProduct(productblocked,id);
        return HttpStatus.OK;
    }


    public HttpStatus releaseStock(Long id, Integer quantity) {
        Product productblocked = getById(id);
        productblocked.setRemainingStock(productblocked.getRemainingStock() + quantity);
        replaceProduct(productblocked,id);
        return HttpStatus.OK;
    }
}
