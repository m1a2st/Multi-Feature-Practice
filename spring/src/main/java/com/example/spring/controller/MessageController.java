package com.example.spring.controller;

import com.example.rabbitmq.models.CommentMsg;
import com.example.rabbitmq.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageProducer messageProducer;

    @PostMapping("/message")
    public void sendMessage(@RequestBody CommentMsg commentMsg) {
        messageProducer.send(commentMsg);
    }
}
