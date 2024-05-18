package com.example.myapplication.Chat;

public class Message {
    private String sender;
    private String content;

    private boolean issender;
    public Message(String sender, String content, boolean issender) {
        this.sender = sender;
        this.content = content;
        this.issender = issender;
    }
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIssender() {
        return issender;
    }

    public void setIssender(boolean issender) {
        this.issender = issender;
    }
}
