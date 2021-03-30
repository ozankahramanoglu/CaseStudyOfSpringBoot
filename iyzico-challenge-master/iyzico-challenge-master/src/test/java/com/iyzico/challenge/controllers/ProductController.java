package com.iyzico.challenge.controllers;


import com.iyzico.challenge.entity.Product;
import com.iyzico.challenge.productService.ProductServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class ProductController {

    @Autowired
    private MockMvc mvc;

    @Test
    public void newProduct() throws Exception{
        mvc.perform(post("http://localhost:9000/Product").
                contentType(MediaType.APPLICATION_JSON).
                content("{\"name\":\"Samsung S10\",\"description\":\"Phone\",\"price\":10000.00,\"remainingStock\":12}"));
    }
    @Test
    public void getAll() throws Exception{
        mvc.perform(get("http://localhost:9000/Product").
                contentType(MediaType.APPLICATION_JSON));
    }
}
