package com.cashregister.demo.dao;

import com.cashregister.demo.model.Product;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createCriteria(Product.class).list();
        session.close();
        return products;
    }

    @Override
    public Product findBySku(String sku) {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, sku);
        Hibernate.initialize(product.getLineItems());
        session.close();
        return product;
    }

    @Override
    public void save(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Product product) {

    }
}
