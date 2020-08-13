package com.cashregister.demo.controller;

import com.cashregister.demo.model.Customer;
import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Transaction;
import com.cashregister.demo.model.TransactionConfig;
import com.cashregister.demo.service.CustomerService;
import com.cashregister.demo.service.TransactionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerService customerService;

    @Autowired

    @RequestMapping(value = "")
    public Map<String, List<Transaction>> listTransactions() {
        Map<String, List<Transaction>> response = new HashMap<>();
        List<Transaction> transactions = transactionService.findAll();
        response.put("transactions", transactions);
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Transaction> createTransaction(@RequestBody TransactionConfig transactionConfig) {
        System.out.println("The user's last name is " + transactionConfig.customer.getLastName());
        System.out.println("The number of products is " + transactionConfig.productList);
        Map<String, Transaction> response = new HashMap<>();
        Transaction transaction = transactionService.create(transactionConfig);
        response.put("transaction", transaction);
        return response;
    }

    /*
    @RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Transaction> createTransaction(@RequestBody JsonNode jsonNode) throws Exception {
        Map<String, Transaction> response = new HashMap<>();
        // Convert json payload to objects.
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.convertValue(jsonNode.get("customer"), Customer.class);
        Product[] products = objectMapper.convertValue(jsonNode.get("products"), Product[].class);

        List<Product> _products = Arrays.asList(products);

        // Make sure the user exists in our system.
        Long customer_id;
        try {
            customer_id = customer.getId();
        } catch (Exception e) {
            System.out.println("Error getting customer ID from user in payload.");
            return null;
        }
        Customer customer_info = customerService.findById(customer_id);
        if(customer_info == null) {
            // Raise 400 status.
            System.out.println("This customer does not exists in our system.");
            return null;
        }

        System.out.println("Creating a transaction...");
        Transaction transaction = new Transaction();

        // Get unique skus to get the product object from the database.
        Set<String> skus = new HashSet<>();
        for (final Product product : products) {
            skus.add(product.getSku());
        }
        System.out.println("Getting information for the following SKUs: " + skus.toString());

        // Verify products in payload are in the database.
        for (Product product : products) {
            System.out.println(product.getSku());
        }

        // Group like items in the products list.

        // Create response object with quantity of item purchased, name, units?, regular price, discounted price, total price to pay.

        Long transactionId = transactionService.save(transaction);
        response.put("transaction", transactionService.findById(transactionId));
        return response;
    }
    */
}
