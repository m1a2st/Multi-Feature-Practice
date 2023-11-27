package com.example.rabbitmq.configs;

import static com.example.rabbitmq.configs.constants.Constants.*;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /*******************************************************************************
     * Queue
     *******************************************************************************
     */
    @Bean(name = BEAN_HELLO_QUEUE)
    public Queue helloQueue() {
        return new Queue(NAME_HELLO_QUEUE);
    }

    @Bean(name = BEAN_INTERNAL_NOTIFICATION_QUEUE)
    public Queue internalNotificationQueue() {
        return new Queue(NAME_INTERNAL_NOTIFICATION_QUEUE);
    }

    @Bean(name = BEAN_EMAIL_NOTIFICATION_QUEUE)
    public Queue emailNotificationQueue() {
        return new Queue(NAME_EMAIL_NOTIFICATION_QUEUE);
    }

    /*******************************************************************************
     * FANOUT Exchange
     *******************************************************************************
     */
    @Bean(name = BEAN_COMMENT_NOTIFICATION_FANOUT_EXCHANGE)
    public FanoutExchange commentNotificationFanoutExchange() {
        return new FanoutExchange(NAME_COMMENT_NOTIFICATION_FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindInternalNotificationQueueToFanoutExchange() {
        return BindingBuilder.bind(internalNotificationQueue()).to(commentNotificationFanoutExchange());
    }

    @Bean
    public Binding bindEmailNotificationQueueToFanoutExchange() {
        return BindingBuilder.bind(emailNotificationQueue()).to(commentNotificationFanoutExchange());
    }

    /*******************************************************************************
     * Direct Exchange
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
     * Routing Exchange
     *******************************************************************************
     */
    @Bean(name = BEAN_COMMENT_NOTIFICATION_ROUTING_EXCHANGE)
    public DirectExchange commentNotificationRoutingExchange() {
        return new DirectExchange(NAME_COMMENT_NOTIFICATION_ROUTING_EXCHANGE);
    }

    @Bean
    public Binding bindInternalNotificationQueueToRoutingExchangeForPostComment() {
        return BindingBuilder.bind(internalNotificationQueue())
                .to(commentNotificationRoutingExchange())
                .with("post.comment");
    }

    @Bean
    public Binding bindInternalNotificationQueueToRoutingExchangeForProductComment() {
        return BindingBuilder.bind(internalNotificationQueue())
                .to(commentNotificationRoutingExchange())
                .with("product.comment");
    }

    @Bean
    public Binding bindEmailNotificationQueueToRoutingExchangeForProductComment() {
        return BindingBuilder.bind(emailNotificationQueue())
                .to(commentNotificationRoutingExchange())
                .with("product.comment");
    }

    /*******************************************************************************
     * Topic Exchange
     *******************************************************************************
     */
    @Bean(name = BEAN_COMMENT_NOTIFICATION_TOPIC_EXCHANGE)
    public TopicExchange commentNotificationTopicExchange() {
        return new TopicExchange(NAME_COMMENT_NOTIFICATION_TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindInternalNotificationQueueToTopicExchangeForPostComment() {
        return BindingBuilder.bind(internalNotificationQueue())
                .to(commentNotificationTopicExchange())
                .with("post.#");
    }

    @Bean
    public Binding bindInternalNotificationQueueToTopicExchangeForProductComment() {
        return BindingBuilder.bind(internalNotificationQueue())
                .to(commentNotificationTopicExchange())
                .with("product.*");
    }

    @Bean
    public Binding bindEmailNotificationQueueToTopicExchangeForProductComment() {
        return BindingBuilder.bind(emailNotificationQueue())
                .to(commentNotificationTopicExchange())
                .with("product.*.#");
    }

    /*******************************************************************************
     * Converter
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
     * RabbitTemplate
     *******************************************************************************
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
