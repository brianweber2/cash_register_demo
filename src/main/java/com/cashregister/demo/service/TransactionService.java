package com.cashregister.demo.service;

import com.cashregister.demo.model.Customer;
import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();
    Transaction findById(Long id);
    void save(Transaction transaction);
    void update(Transaction transaction);
    void delete(Transaction transaction);
//    Transaction create(Customer customer, List<Product> products);
}
