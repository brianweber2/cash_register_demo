package com.cashregister.demo.controller;

import com.cashregister.demo.model.Customer;
import com.cashregister.demo.model.Product;
import com.cashregister.demo.service.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    @SuppressWarnings("unchecked")
    public Map<String, List<Product>> listProducts() {
        Map<String, List<Product>> response = new HashMap<>();
        List<Product> products = productService.findAll();
        response.put("products", products);
        return response;
    }

    @RequestMapping("/products/{sku}")
    public Map<String, Product> getProduct(@PathVariable String sku) {
        Map<String, Product> response = new HashMap<>();
        Product product = productService.findBySku(sku);
        response.put("product", product);
        return response;
    }

    @RequestMapping(value = "/products/create_receipt", method = RequestMethod.POST, consumes = "application/json")
    public void createReceipt(@RequestBody JsonNode jsonNode) throws Exception {
        // Convert json payload to objects.
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.convertValue(jsonNode.get("customer"), Customer.class);
        Product[] products = objectMapper.convertValue(jsonNode.get("products"), Product[].class);

        // Group like items in the products list.

        // Create response object with quantity of item purchased, name, units?, regular price, discounted price, total price to pay.

        for (Product product : products) {
            System.out.println(product.getName());
        }
    }
}
