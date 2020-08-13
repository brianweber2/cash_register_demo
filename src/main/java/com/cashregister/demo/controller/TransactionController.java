package com.cashregister.demo.controller;

import com.cashregister.demo.model.Customer;
import com.cashregister.demo.model.Item;
import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Transaction;
import com.cashregister.demo.service.CustomerService;
import com.cashregister.demo.service.ProductService;
import com.cashregister.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired

    @RequestMapping(value = "")
    public Map<String, List<Transaction>> listTransactions() {
        Map<String, List<Transaction>> response = new HashMap<>();
        List<Transaction> transactions = transactionService.findAll();
        response.put("transactions", transactions);
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Transaction> createTransaction(@RequestBody List<Product> products, @RequestParam("user_id") Long customerId) {
        Map<String, Transaction> response = new HashMap<>();

        Customer customer = customerService.findById(customerId);

        boolean hasLoyaltyRewards = false;
        if(!customer.getLoyaltyNumber().isEmpty()) {
            hasLoyaltyRewards = true;
        }
        System.out.printf("Customer %s has loyalty awards? %b%n", customer.getLastName(), hasLoyaltyRewards);

        // Get unique skus to get the product objects from the database.
        Set<String> skus = new HashSet<>();
        for (final Product product : products) {
            skus.add(product.getSku());
        }

        List<String> skusStrings = new ArrayList<String>();
        for(String sku : skus) {
            skusStrings.add(sku);
        }
        List<Product> productsFromDb = productService.findBySkus(skusStrings);
        // Create Map with <sku, Product>
        Map<String, Product> productMapBySku = new HashMap<>();
        for(Product product : productsFromDb) {
            productMapBySku.put(product.getSku(), product);
        }

        // Loop through products in payload to calculate quantity and total.
        List<Item> items = new ArrayList<>();
        double total = 23.95;
        Transaction t = new Transaction(total, customer, items);

//        Long transactionId = transactionService.save(t);
//        Transaction transaction = transactionService.findById(transactionId);
//        response.put("transaction", transaction);
        return response;
    }
}
