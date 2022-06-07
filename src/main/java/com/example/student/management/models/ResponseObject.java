package com.example.student.management.models;

public class ResponseObject {
    private Status status;
    private String message;
    private Object data;
    public enum Status {
        STATUS_OK, STATUS_FAILED
    }

    public ResponseObject(Object data, String message, Status status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
