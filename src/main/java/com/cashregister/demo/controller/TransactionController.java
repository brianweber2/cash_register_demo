package com.cashregister.demo.controller;

import com.cashregister.demo.model.Customer;
import com.cashregister.demo.model.Item;
import com.cashregister.demo.model.Product;
import com.cashregister.demo.model.Transaction;
import com.cashregister.demo.service.CustomerService;
import com.cashregister.demo.service.ItemService;
import com.cashregister.demo.service.ProductService;
import com.cashregister.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private ItemService itemService;

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

        // Get unique skus to get the product objects from the database (source of truth).
        Set<String> skus = new HashSet<>();
        for (final Product product : products) {
            skus.add(product.getSku());
        }
        List<String> skusStrings = new ArrayList<String>();
        for(String sku : skus) {
            skusStrings.add(sku);
        }
        List<Product> productsFromDb = productService.findBySkus(skusStrings);

        // Loop through products in payload to calculate quantity.
        List<Item> items = new ArrayList<>();
        Map<String, Integer> productQuantity = new HashMap<>();
        for(Product product : products) {
            if(productQuantity.containsKey(product.getSku())) {
                productQuantity.put(product.getSku(), productQuantity.get(product.getSku()) + 1);
            } else {
                productQuantity.put(product.getSku(), 1);
            }
        }

        // Calculate total cost
        double total = 0;
        for(Product product : productsFromDb) {
            if(!customer.getLoyaltyNumber().isEmpty()) {
                total += product.getDiscountPrice() * productQuantity.get(product.getSku());
            } else {
                total += product.getDefaultPrice() * productQuantity.get(product.getSku());
            }
        }

        // Calculate item total and append to Items list
        Map<String, Double> productTotal = new HashMap<>();
        for(Product product : productsFromDb) {
            if(!customer.getLoyaltyNumber().isEmpty()) {
                productTotal.put(product.getSku(), productQuantity.get(product.getSku()) * product.getDiscountPrice());
            } else {
                productTotal.put(product.getSku(), productQuantity.get(product.getSku()) * product.getDefaultPrice());
            }
        }
        for(Product product : productsFromDb) {
            Item item = new Item();
            item.setTotal(productTotal.get(product.getSku()));
            item.setProduct(product);
            item.setQuantity(productQuantity.get(product.getSku()));
            items.add(item);
        }

        Transaction t = new Transaction();
        t.setTotal(total);
        t.setCustomer(customer);
        t.setItems(items);

        Long transactionId = transactionService.save(t);
        Transaction transaction = transactionService.findById(transactionId);
        response.put("transaction", t);
        return response;
    }
}
