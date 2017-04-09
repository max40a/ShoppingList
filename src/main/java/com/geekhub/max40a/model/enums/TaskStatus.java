package com.geekhub.max40a.model.enums;

public enum TaskStatus {
    INCOMPLETE("INCOMPLETE"),
    COMPLETE("COMPLETE");

    private String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatusToString() {
        return status;
    }
}