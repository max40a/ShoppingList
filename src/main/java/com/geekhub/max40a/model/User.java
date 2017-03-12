package com.geekhub.max40a.model;

import java.util.List;

public class User extends BaseEntity {

    private String name;
    private String email;
    private String password;
    private List<Task> tasks;

    public User() {
    }

    public User(String name, String email, String password, List<Task> tasks) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}