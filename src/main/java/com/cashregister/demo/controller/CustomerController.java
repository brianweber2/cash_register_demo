package com.cashregister.demo.controller;

import com.cashregister.demo.model.Customer;
import com.cashregister.demo.model.Transaction;
import com.cashregister.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    @SuppressWarnings("unchecked")
    public Map<String, List<Customer>> listCustomers() {
        Map<String, List<Customer>> response = new HashMap<>();
        List<Customer> customers = customerService.findAll();
        response.put("customers", customers);
        return response;
    }
}
