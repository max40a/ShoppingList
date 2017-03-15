package com.geekhub.max40a.service;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasksByUser(Integer userId);

    List<Item> getItemByTask(Integer taskId);

    void createTask(Task task);

    void deleteTask(Integer taskId);
}
