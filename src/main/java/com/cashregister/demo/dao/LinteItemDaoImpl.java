package com.cashregister.demo.dao;

import com.cashregister.demo.model.LineItem;
import com.cashregister.demo.model.Product;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LinteItemDaoImpl implements LineItemDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<LineItem> findAll() {
        Session session = sessionFactory.openSession();
        List<LineItem> lineItems = session.createCriteria(LineItem.class).list();
        session.close();
        return lineItems;
    }

    @Override
    public LineItem findById(Long id) {
        Session session = sessionFactory.openSession();
        LineItem lineItem = session.get(LineItem.class, id);
        session.close();
        return lineItem;
    }

    @Override
    public void save(LineItem lineItem) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(lineItem);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(LineItem lineItem) {

    }
}
