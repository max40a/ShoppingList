package com.geekhub.max40a.dto;

import com.geekhub.max40a.model.Task;

import java.util.List;

public class TaskResponseDto {
    private List<Task> taskList;
    private Integer countPage;

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Integer getCountPage() {
        return countPage;
    }

    public void setCountPage(Integer countPage) {
        this.countPage = countPage;
    }
}
