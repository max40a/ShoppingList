package com.geekhub.max40a.model.enums;

public enum TaskStatus {
    INCOMPLETE("Incomplete"),
    COMPLETE("Complete");

    private String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatusToString() {
        return status;
    }
}