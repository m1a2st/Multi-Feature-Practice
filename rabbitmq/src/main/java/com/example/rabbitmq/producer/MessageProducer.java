package com.example.rabbitmq.producer;

import com.example.rabbitmq.models.CommentMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void send(CommentMsg message) {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend("hello", message);
        log.info("Message sent! {}", getClass().getSimpleName());
    }
}
