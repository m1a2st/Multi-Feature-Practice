package com.example.rabbitmq.consumer;

import static com.example.rabbitmq.configs.constants.Constants.NAME_INTERNAL_NOTIFICATION_QUEUE;

import com.example.rabbitmq.models.CommentMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InternalNotificationConsumer {

    @RabbitListener(queues = NAME_INTERNAL_NOTIFICATION_QUEUE)
    public void processMsg(@Payload CommentMsg comment) {
        log.info("站內通知：" + comment.getContent());
    }
}
