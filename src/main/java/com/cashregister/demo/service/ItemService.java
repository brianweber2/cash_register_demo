package com.cashregister.demo.service;

import com.cashregister.demo.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item findById(Long id);
    Long save(Item item);
    void delete(Item item);
}
