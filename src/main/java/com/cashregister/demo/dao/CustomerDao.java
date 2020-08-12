package com.cashregister.demo.dao;

import com.cashregister.demo.model.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
}
