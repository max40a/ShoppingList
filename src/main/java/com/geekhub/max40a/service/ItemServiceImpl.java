package com.geekhub.max40a.service;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void addItemInTask(Item item, Integer taskId) {
        itemRepository.addItemInTask(item, taskId);
    }

    @Override
    public void deleteItemInTask(Integer itemId) {
        itemRepository.deleteItemInTask(itemId);
    }
}