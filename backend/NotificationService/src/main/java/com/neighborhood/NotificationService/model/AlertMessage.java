package com.neighborhood.NotificationService.model;


public class AlertMessage {
    private String content;

    public AlertMessage() {}

    public AlertMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

