package com.cashregister.demo.service;

import com.cashregister.demo.dao.TransactionDao;
import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionDao transactionDao;

    @Override
    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionDao.findById(id);
    }

    @Override
    public Long save(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        transactionDao.delete(transaction);
    }

    @Override
    public Transaction create(Long userId, List<Product> products) {
        return transactionDao.create(userId, products);
    }
}
