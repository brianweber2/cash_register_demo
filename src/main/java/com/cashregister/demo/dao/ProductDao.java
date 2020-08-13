package com.cashregister.demo.dao;

import com.cashregister.demo.model.Product;

import java.util.HashSet;
import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findBySku(String sku);
    List<Product> findBySkus(List<String> skus);
    void save(Product product);
    void delete(Product product);
}
