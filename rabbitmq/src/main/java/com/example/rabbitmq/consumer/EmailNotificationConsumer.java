package com.example.rabbitmq.consumer;

import com.example.rabbitmq.models.CommentMsg;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.rabbitmq.configs.constants.Constants.NAME_EMAIL_NOTIFICATION_QUEUE;

@Component
public class EmailNotificationConsumer {

    @RabbitListener(queues = NAME_EMAIL_NOTIFICATION_QUEUE)
    public void processMsg(@Payload CommentMsg comment) {
        System.out.println("Email 通知：" + comment.getContent());
    }
}
