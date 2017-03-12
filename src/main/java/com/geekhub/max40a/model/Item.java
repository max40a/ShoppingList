package com.geekhub.max40a.model;

import com.geekhub.max40a.model.enums.ItemStatus;

import java.util.List;

public class Item extends BaseEntity {

    private List<Task> tasks;
    private String description;
    private ItemStatus status;

    public Item() {
    }

    public Item(List<Task> tasks, String description, ItemStatus status) {
        this.tasks = tasks;
        this.description = description;
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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