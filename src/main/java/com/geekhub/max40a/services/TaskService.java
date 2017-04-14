package com.geekhub.max40a.services;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.Task;
import com.geekhub.max40a.model.enums.TaskStatus;

import java.util.List;

public interface TaskService {

    List<Task> getTasksByUser(Integer userId, Integer offset, Integer tasksPerPage);

    List<Item> getItemByTask(Integer taskId);

    void createTask(Task task);

    void deleteTask(Integer taskId);

    void changeStatusOfTask(Integer taskId, String status);

    Integer countAllTaskOfUser(Integer userId);
}
