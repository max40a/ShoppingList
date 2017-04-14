package com.geekhub.max40a.dto;

import com.geekhub.max40a.model.Task;

import java.util.List;

public class TaskResponseDto {
    private final List<Task> taskList;
    private final Integer countPage;

    public TaskResponseDto(List<Task> taskList, Integer countPage) {
        this.taskList = taskList;
        this.countPage = countPage;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public Integer getCountPage() {
        return countPage;
    }
}
