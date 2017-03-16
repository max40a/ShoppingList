package com.geekhub.max40a.service;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.Task;
import com.geekhub.max40a.model.enums.TaskStatus;
import com.geekhub.max40a.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasksByUser(Integer userId) {
        return taskRepository.getTaskByUser(userId);
    }

    @Override
    public List<Item> getItemByTask(Integer taskId) {
        return taskRepository.getItemByTask(taskId);
    }

    @Override
    public void createTask(Task task) {
        taskRepository.addTask(task);
    }

    @Override
    public void deleteTask(Integer taskId) {
        taskRepository.deleteTask(taskId);
    }

    @Override
    public void changeStatusOfTask(Integer taskId, TaskStatus status) {
        taskRepository.changeStatusOfTask(taskId, status);
    }
}