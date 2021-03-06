package com.geekhub.max40a.controller;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.enums.ItemStatus;
import com.geekhub.max40a.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/host/tasks/items")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addItemInTask(Integer taskId, String description) {
        Item item = new Item();
        item.setDescription(description);
        item.setStatus(ItemStatus.UNREADY);

        itemService.addItemInTask(item, taskId);
    }

    @DeleteMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Integer itemId) {
        itemService.deleteItemInTask(itemId);
    }

    @PutMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void changeItemStatus(@PathVariable Integer itemId, String status) {
        itemService.changeItemStatus(itemId, status);
    }
}