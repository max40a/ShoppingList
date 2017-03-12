package com.geekhub.max40a.model;

import com.geekhub.max40a.model.enums.ItemStatus;

public class Item extends BaseEntity {

    private String description;
    private ItemStatus status;

    public Item() {
    }

    public Item(String description, ItemStatus status) {
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}