package com.geekhub.max40a.model;

import com.geekhub.max40a.model.enums.TaskStatus;

import java.util.List;

public class Task extends BaseEntity {

    private TaskStatus taskStatus;
    private String title;
    private List<Item> items;

    public Task() {
    }

    public Task(TaskStatus taskStatus, String title, List<Item> items) {
        this.taskStatus = taskStatus;
        this.title = title;
        this.items = items;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}