package com.geekhub.max40a.model;

import com.geekhub.max40a.model.enums.ItemStatus;

public class Item extends BaseEntity {

    private String description;
    private ItemStatus status;
    private Integer taskId;

    public Item() {
    }

    public Item(String description, ItemStatus status, Integer taskId) {
        this.description = description;
        this.status = status;
        this.taskId = taskId;
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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}