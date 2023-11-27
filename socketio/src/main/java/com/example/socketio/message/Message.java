package com.example.socketio.message;

import lombok.Data;

@Data
public class Message {

    private MessageType type;
    private String content;

    public enum MessageType {
        SERVER,
        CLIENT
    }
}
