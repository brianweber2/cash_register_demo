package com.cashregister.demo.dao;

import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Transaction> findAll() {
        Session session = sessionFactory.openSession();
        List<Transaction> transactions = session.createCriteria(Transaction.class).list();
        session.close();
        return transactions;
    }

    @Override
    public Transaction findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.get(Transaction.class, id);
        session.close();
        return transaction;
    }

    @Override
    public Long save(Transaction transaction) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(transaction);
        session.getTransaction().commit();
        session.close();
        return transaction.getId();
    }

    @Override
    public void delete(Transaction transaction) {

    }

    @Override
    public Transaction create(Long userId, List<Product> products) {
        return null;
    }
}
