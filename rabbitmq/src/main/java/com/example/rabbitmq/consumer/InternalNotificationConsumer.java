package com.example.rabbitmq.consumer;

import com.example.rabbitmq.models.CommentMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.rabbitmq.constants.Constants.NAME_INTERNAL_NOTIFICATION_QUEUE;

@Component
public class InternalNotificationConsumer {

    @RabbitListener(queues = NAME_INTERNAL_NOTIFICATION_QUEUE)
    public void processMsg(@Payload CommentMsg comment) {
        System.out.println("站內通知：" + comment.getContent());
    }
}
