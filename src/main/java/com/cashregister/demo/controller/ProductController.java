package com.cashregister.demo.controller;

import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Transaction;
import com.cashregister.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    @SuppressWarnings("unchecked")
    public Map<String, List<Product>> listProducts() {
        Map<String, List<Product>> response = new HashMap<>();
        List<Product> products = productService.findAll();
        response.put("products", products);
        return response;
    }

    @RequestMapping("/products/{sku}")
    public Map<String, Product> getProduct(@PathVariable String sku) {
        Map<String, Product> response = new HashMap<>();
        Product product = productService.findBySku(sku);
        response.put("product", product);
        return response;
    }
}
