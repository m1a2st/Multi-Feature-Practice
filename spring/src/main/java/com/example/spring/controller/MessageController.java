package com.example.spring.controller;

import com.example.rabbitmq.models.CommentMsg;
import com.example.rabbitmq.models.SpecialMsg;
import com.example.rabbitmq.producer.MessageProducer;
import com.example.rabbitmq.producer.SpecialProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageProducer messageProducer;
    private final SpecialProducer specialProducer;

    @PostMapping("/message")
    public void sendMessage(@RequestBody CommentMsg commentMsg) {
        messageProducer.send(commentMsg);
    }

    @PostMapping("/special")
    public void sendSpecialMessage(@RequestBody SpecialMsg specialMsg) {
        specialProducer.sendNewCommentNotification(specialMsg);
    }
}
