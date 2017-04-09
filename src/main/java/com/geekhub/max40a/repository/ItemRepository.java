package com.geekhub.max40a.repository;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.enums.ItemStatus;

public interface ItemRepository {

    void addItemInTask(Item item, Integer taskId);

    void deleteItemInTask(Integer itemId);

    void changeItemStatus(Integer itemId, ItemStatus status);
}