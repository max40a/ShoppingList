package com.geekhub.max40a.model.enums;

public enum  ItemStatus {
    READY("Ready"),
    UNREADY("Unready");

    private String status;

    ItemStatus(String status) {
        this.status = status;
    }

    public String getStatusToString() {
        return status;
    }
}