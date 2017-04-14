package com.geekhub.max40a.services.impls;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.Task;
import com.geekhub.max40a.model.enums.TaskStatus;
import com.geekhub.max40a.repository.TaskRepository;
import com.geekhub.max40a.services.TaskService;
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
    public List<Task> getTasksByUser(Integer userId, Integer offset, Integer tasksPerPage) {
        return taskRepository.getTaskByUser(userId, offset, tasksPerPage);
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
    public void changeStatusOfTask(Integer taskId, String status) {
        String changeableStatus = "";
        if (status.equals("INCOMPLETE")) {
            changeableStatus = "COMPLETE";
        } else if (status.equals("COMPLETE")) {
            changeableStatus = "INCOMPLETE";
        }

        taskRepository.changeStatusOfTask(taskId, TaskStatus.valueOf(changeableStatus));
    }

    @Override
    public Integer countAllTaskOfUser(Integer userId) {
        return taskRepository.countAllTaskOfUser(userId);
    }
}