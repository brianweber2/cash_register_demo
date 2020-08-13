package com.cashregister.demo.dao;

import com.cashregister.demo.model.Item;
import com.cashregister.demo.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Item> findAll() {
        Session session = sessionFactory.openSession();
        List<Item> items = session.createCriteria(Item.class).list();
        session.close();
        return items;
    }

    @Override
    public Item findById(Long id) {
        Session session = sessionFactory.openSession();
        Item item = session.get(Item.class, id);
        session.close();
        return item;
    }

    @Override
    public Long save(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(item);
        session.getTransaction().commit();
        session.close();
        return item.getId();
    }

    @Override
    public void delete(Item item) {

    }
}
