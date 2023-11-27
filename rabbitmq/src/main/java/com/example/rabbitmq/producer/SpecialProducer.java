package com.example.rabbitmq.producer;

import static com.example.rabbitmq.configs.constants.Constants.NAME_COMMENT_NOTIFICATION_DIRECT_EXCHANGE;
import static com.example.rabbitmq.configs.constants.Constants.NAME_COMMENT_NOTIFICATION_FANOUT_EXCHANGE;
import static com.example.rabbitmq.models.SpecialMsg.Mode.DIRECT;

import com.example.rabbitmq.models.SpecialMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecialProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendNewCommentNotification(SpecialMsg comment) {
        String exchange;
        String routingKey;
        if (DIRECT.equals(comment.getMode())) {
            exchange = NAME_COMMENT_NOTIFICATION_DIRECT_EXCHANGE;
            routingKey = comment.getRoutingKey();
        } else {
            exchange = NAME_COMMENT_NOTIFICATION_FANOUT_EXCHANGE;
            routingKey = "";
        }

        rabbitTemplate.convertAndSend(exchange, routingKey, comment);
    }
}
