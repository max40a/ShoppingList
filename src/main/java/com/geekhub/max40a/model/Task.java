package com.geekhub.max40a.model;

import com.geekhub.max40a.model.enums.TaskStatus;

import java.util.List;

public class Task extends BaseEntity {

    private TaskStatus taskStatus;
    private String title;
    private Integer userId;
    private List<Item> items;

    public Task() {
    }

    public Task(TaskStatus taskStatus, String title, Integer userId ,List<Item> items) {
        this.taskStatus = taskStatus;
        this.title = title;
        this.items = items;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}