package com.geekhub.max40a.services;

import com.geekhub.max40a.model.Item;

public interface ItemService {

    void addItemInTask(Item item, Integer taskId);

    void deleteItemInTask(Integer itemId);

    void changeItemStatus(Integer itemId);
}