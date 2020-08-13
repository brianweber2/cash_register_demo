package com.cashregister.demo.service;

import com.cashregister.demo.dao.LineItemDao;
import com.cashregister.demo.model.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineItemServiceImpl implements LineItemService {
    @Autowired
    private LineItemDao lineItemDao;

    @Override
    public List<LineItem> findAll() {
        return lineItemDao.findAll();
    }

    @Override
    public LineItem findById(Long id) {
        return lineItemDao.findById(id);
    }

    @Override
    public void save(LineItem lineItem) {
        lineItemDao.save(lineItem);
    }

    @Override
    public void delete(LineItem lineItem) {
        lineItemDao.delete(lineItem);
    }
}
