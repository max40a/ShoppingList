package com.geekhub.max40a.controller;

import com.geekhub.max40a.dto.TaskResponseDto;
import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.Task;
import com.geekhub.max40a.model.enums.TaskStatus;
import com.geekhub.max40a.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/host/tasks")
public class TaskController {

    private static Integer TASKS_PER_PAGE = 2;

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/user/{userId}")
    public TaskResponseDto getUserTasksDto(@PathVariable Integer userId) {
        TaskResponseDto dto = new TaskResponseDto();

        List<Task> tasks = taskService.getTasksByUser(userId);
        Integer tasksCount = taskService.countAllTaskOfUser(userId);
        Integer pageCount = (tasksCount % TASKS_PER_PAGE == 0) ? (tasksCount / TASKS_PER_PAGE) : (tasksCount / TASKS_PER_PAGE) + 1;

        dto.setTaskList(tasks);
        dto.setCountPage(pageCount);

        return dto;
    }

    @GetMapping(value = "/{taskId}")
    public List<Item> getItemsByTask(@PathVariable Integer taskId) {
        return taskService.getItemByTask(taskId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(String title, Integer userId) {
        Task task = new Task();
        task.setTitle(title);
        task.setStatus(TaskStatus.INCOMPLETE);
        task.setUserId(userId);

        taskService.createTask(task);
    }

    @DeleteMapping(value = "/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskById(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping(value = "/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void changeTaskStatus(@PathVariable Integer taskId, String status) {
        taskService.changeStatusOfTask(taskId, status);
    }
}