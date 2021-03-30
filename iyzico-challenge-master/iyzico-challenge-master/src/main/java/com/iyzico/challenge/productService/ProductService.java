package com.iyzico.challenge.productService;

import com.iyzico.challenge.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface ProductService {
    public abstract Collection<Product> getAll();
    public abstract Product newProduct(Product newProduct);
    public abstract Collection<Product> getByIds (List<Long> ids);
    public abstract Product getById (Long id);
    public abstract Product replaceProduct (Product newProduct, Long id);
    public abstract HttpStatus deleteProduct (Long id);
    public abstract HttpStatus blockStock (Long id,Integer stock);
    public abstract HttpStatus releaseStock (Long id, Integer stock);
}
