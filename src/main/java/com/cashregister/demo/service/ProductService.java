package com.cashregister.demo.service;

import com.cashregister.demo.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findBySku(String sku);
    void save(Product product);
    void delete(Product product);
}
