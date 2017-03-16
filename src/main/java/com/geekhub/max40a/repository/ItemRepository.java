package com.geekhub.max40a.repository;

import com.geekhub.max40a.model.Item;

public interface ItemRepository {

    void addItemInTask(Item item, Integer taskId);

    void deleteItemInTask(Integer itemId);
}