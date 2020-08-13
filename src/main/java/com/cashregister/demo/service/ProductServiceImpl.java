package com.cashregister.demo.service;

import com.cashregister.demo.dao.ProductDao;
import com.cashregister.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findBySku(String sku) {
        return productDao.findBySku(sku);
    }

    @Override
    public List<Product> findBySkus(List<String> skus) {
        return productDao.findBySkus(skus);
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }
}
