package com.cashregister.demo.dao;

import com.cashregister.demo.model.Item;

import java.util.List;

public interface ItemDao {
    List<Item> findAll();
    Item findById(Long id);
    Long save(Item item);
    void delete(Item item);
}
