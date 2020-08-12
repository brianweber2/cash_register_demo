package com.cashregister.demo.controller;

import com.cashregister.demo.model.Transaction;
import com.cashregister.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/transactions")
    public Map<String, List<Transaction>> listTransactions() {
        Map<String, List<Transaction>> response = new HashMap<>();
        List<Transaction> transactions = transactionService.findAll();
        response.put("transactions", transactions);
        return response;
    }
}
