package com.cashregister.demo.dao;

import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Transaction;

import java.util.List;

public interface TransactionDao {
    List<Transaction> findAll();
    Transaction findById(Long id);
    Long save(Transaction transaction);
    void delete(Transaction transaction);
    Transaction create(Long userId, List<Product> products);
}
