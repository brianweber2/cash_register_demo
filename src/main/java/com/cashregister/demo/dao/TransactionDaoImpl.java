package com.cashregister.demo.dao;

import com.cashregister.demo.model.Customer;
import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return null;
    }

    @Override
    public void save(Transaction transaction) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(transaction);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Transaction transaction) {

    }

    @Override
    public void delete(Transaction transaction) {

    }

//    @Override
//    public Transaction create(Customer customer, List<Product> products) {
//        return null;
//    }
}
