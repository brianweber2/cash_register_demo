package com.cashregister.demo.dao;

import com.cashregister.demo.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findBySku(String sku);
    void save(Product product);
    void update(Product product);
    void delete(Product product);
}
