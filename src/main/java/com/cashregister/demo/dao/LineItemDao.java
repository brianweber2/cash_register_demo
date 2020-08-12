package com.cashregister.demo.dao;

import com.cashregister.demo.model.LineItem;

import java.util.List;

public interface LineItemDao {
    List<LineItem> findAll();
    LineItem findById(Long id);
    void save(LineItem lineItem);
    void update(LineItem lineItem);
    void delete(LineItem lineItem);
}
