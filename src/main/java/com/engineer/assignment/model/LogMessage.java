package com.engineer.assignment.model;

public class LogMessage {
    private long timestamp;
    private String app;
    private String message;


    public LogMessage(long timestamp, String app, String message) {
        this.timestamp = timestamp;
        this.app = app;
        this.message = message;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public String getApp() {
        return app;
    }

    public String getMessage() {
        return message;
    }
}