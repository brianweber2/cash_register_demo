package com.cashregister.demo.service;

import com.cashregister.demo.dao.ItemDao;
import com.cashregister.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }

    @Override
    public Item findById(Long id) {
        return itemDao.findById(id);
    }

    @Override
    public Long save(Item item) {
        return itemDao.save(item);
    }

    @Override
    public void delete(Item item) {
        itemDao.delete(item);
    }
}
