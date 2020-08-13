package com.cashregister.demo.service;

import com.cashregister.demo.model.Transaction;
import com.cashregister.demo.model.TransactionConfig;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();
    Transaction findById(Long id);
    Long save(Transaction transaction);
    void delete(Transaction transaction);
    Transaction create(TransactionConfig transactionConfig);
}
