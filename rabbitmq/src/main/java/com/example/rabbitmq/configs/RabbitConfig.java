package com.example.rabbitmq.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.rabbitmq.configs.constants.Constants.*;

@Configuration
public class RabbitConfig {

    /*******************************************************************************
     * Queue                                                                       *
     *******************************************************************************
     */
    @Bean
    public Queue helloQueue() {
        return new Queue(HELLO_QUEUE);
    }

    @Bean
    public Queue internalNotificationQueue() {
        return new Queue(NAME_INTERNAL_NOTIFICATION_QUEUE);
    }

    @Bean
    public Queue emailNotificationQueue() {
        return new Queue(NAME_EMAIL_NOTIFICATION_QUEUE);
    }

    /*******************************************************************************
     * FANOUT Exchange                                                             *
     *******************************************************************************
     */

    @Bean(name = BEAN_COMMENT_NOTIFICATION_FANOUT_EXCHANGE)
    public FanoutExchange commentNotificationFanoutExchange() {
        return new FanoutExchange(NAME_COMMENT_NOTIFICATION_FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindInternalNotificationQueueToFanoutExchange() {
        return BindingBuilder.bind(internalNotificationQueue())
                .to(commentNotificationFanoutExchange());
    }

    @Bean
    public Binding bindEmailNotificationQueueToFanoutExchange() {
        return BindingBuilder.bind(emailNotificationQueue())
                .to(commentNotificationFanoutExchange());
    }

    /*******************************************************************************
     * Direct Exchange                                                             *
     *******************************************************************************
     */
    @Bean(name = BEAN_COMMENT_NOTIFICATION_DIRECT_EXCHANGE)
    public DirectExchange commentNotificationDirectExchange() {
        return new DirectExchange(NAME_COMMENT_NOTIFICATION_DIRECT_EXCHANGE);
    }

    @Bean
    public Binding bindInternalNotificationQueueToDirectExchange() {
        return BindingBuilder.bind(internalNotificationQueue())
                .to(commentNotificationDirectExchange())
                .with("Internal");
    }

    @Bean
    public Binding bindEmailNotificationQueueToDirectExchange() {
        return BindingBuilder.bind(emailNotificationQueue())
                .to(commentNotificationDirectExchange())
                .with("Email");
    }

    /*******************************************************************************
     * Converter                                                                   *
     *******************************************************************************
     */

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setAlwaysConvertToInferredType(true);
        converter.setDefaultCharset("UTF-8");
        return converter;
    }

    /*******************************************************************************
     * RabbitTemplate                                                              *
     *******************************************************************************
     */

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
