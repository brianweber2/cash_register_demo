package com.cashregister.demo.dao;

import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Product_;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.lang.reflect.Type;
import java.util.HashSet;
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
        session.close();
        return product;
    }

    @Override
    public List<Product> findBySkus(List<String> skus) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        criteriaQuery.select(productRoot);

        criteriaQuery.where(builder.and(productRoot.get(Product_.sku).in(skus)));
        TypedQuery<Product> productTypedQuery = session.createQuery(criteriaQuery);
        return productTypedQuery.getResultList();
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
