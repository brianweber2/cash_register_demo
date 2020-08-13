package com.cashregister.demo.service;

import com.cashregister.demo.model.LineItem;

import java.util.List;

public interface LineItemService {
    List<LineItem> findAll();
    LineItem findById(Long id);
    void save(LineItem lineItem);
    void delete(LineItem lineItem);
}
