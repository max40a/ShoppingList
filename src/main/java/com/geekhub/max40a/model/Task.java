package com.geekhub.max40a.model;

import com.geekhub.max40a.model.enums.TaskStatus;

import java.util.List;

public class Task extends BaseEntity {

    private List<User> users;
    private TaskStatus taskStatus;
    private String title;

    public Task() {
    }

    public Task(List<User> users, TaskStatus taskStatus, String title) {
        this.users = users;
        this.taskStatus = taskStatus;
        this.title = title;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
}