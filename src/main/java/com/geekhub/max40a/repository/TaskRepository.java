package com.geekhub.max40a.repository;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.Task;
import com.geekhub.max40a.model.enums.TaskStatus;

import java.util.List;

public interface TaskRepository {

    List<Task> getTaskByUser(Integer userId);

    List<Item> getItemByTask(Integer taskId);

    void addTask(Task task);

    void deleteTask(Integer id);

    void changeStatusOfTask(Integer taskId, TaskStatus status);
}