package com.example.rabbitmq.configs.constants;

public class Constants {
    /*******************************************************************************
     * Queue
     *******************************************************************************
     */
    public static final String BEAN_HELLO_QUEUE = "helloQueue";
    public static final String BEAN_INTERNAL_NOTIFICATION_QUEUE = "internalNotification";
    public static final String BEAN_EMAIL_NOTIFICATION_QUEUE = "emailNotification";

    public static final String NAME_HELLO_QUEUE = "hello";
    public static final String NAME_INTERNAL_NOTIFICATION_QUEUE = "Comment Internal Notification Queue";
    public static final String NAME_EMAIL_NOTIFICATION_QUEUE = "Comment Email Notification Queue";

    /*******************************************************************************
     * Exchange
     *******************************************************************************
     */
    public static final String BEAN_COMMENT_NOTIFICATION_FANOUT_EXCHANGE = "commentNotificationFanoutExchange";
    public static final String BEAN_COMMENT_NOTIFICATION_DIRECT_EXCHANGE = "commentNotificationDirectExchange";
    public static final String BEAN_COMMENT_NOTIFICATION_ROUTING_EXCHANGE = "commentNotificationRoutingExchange";
    public static final String BEAN_COMMENT_NOTIFICATION_TOPIC_EXCHANGE = "commentNotificationTopicExchange";

    public static final String NAME_COMMENT_NOTIFICATION_FANOUT_EXCHANGE = "Comment Notification Fanout Exchange";
    public static final String NAME_COMMENT_NOTIFICATION_DIRECT_EXCHANGE = "Comment Notification Direct Exchange";
    public static final String NAME_COMMENT_NOTIFICATION_ROUTING_EXCHANGE = "Comment Notification Routing Exchange";
    public static final String NAME_COMMENT_NOTIFICATION_TOPIC_EXCHANGE = "Comment Notification Topic Exchange";
}
