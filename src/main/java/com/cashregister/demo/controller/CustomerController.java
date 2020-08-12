package com.cashregister.demo.controller;

import com.cashregister.demo.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping("/customers")
    @SuppressWarnings("unchecked")
    public Map<String, List<Customer>> listCustomers() {
        Map<String, List<Customer>> response = new HashMap<>();
        Session session = sessionFactory.openSession();
        List<Customer> customers = session.createCriteria(Customer.class).list();
        response.put("customers", customers);
        return response;
    }

    @RequestMapping(value = "/customers/create", method = RequestMethod.POST)
    public String createCustomer() {
        return null;
    }

    @RequestMapping(value = "/customers/create/loyaltynumber", method = RequestMethod.PUT)
    public String addUpdateLoyaltyNumber() {
        return null;
    }
}
