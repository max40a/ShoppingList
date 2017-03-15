package com.geekhub.max40a.controller;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.Task;
import com.geekhub.max40a.model.enums.TaskStatus;
import com.geekhub.max40a.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/host/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/user/{userId}")
    public List<Task> getUserTasks(@PathVariable Integer userId) {
        return taskService.getTasksByUser(userId);
    }

    @GetMapping(value = "/{taskId}")
    public List<Item> getItemsByTask(@PathVariable Integer taskId) {
        return taskService.getItemByTask(taskId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(String title, String status, Integer userId) {
        Task task = new Task();
        task.setTitle(title);
        task.setStatus(TaskStatus.valueOf(status));
        task.setUserId(userId);

        taskService.createTask(task);
    }

    @DeleteMapping(value = "/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaTaskById(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
    }
}