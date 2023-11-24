package com.example.rabbitmq.constants;

public class Constants {
    public static final String HELLO_QUEUE = "hello";
    public static final String BEAN_INTERNAL_NOTIFICATION_QUEUE = "internalNotification";
    public static final String BEAN_EMAIL_NOTIFICATION_QUEUE = "emailNotification";
    public static final String BEAN_COMMENT_NOTIFICATION_FANOUT_EXCHANGE = "commentNotificationFanoutExchange";
    public static final String BEAN_COMMENT_NOTIFICATION_DIRECT_EXCHANGE = "commentNotificationDirectExchange";

    public static final String NAME_INTERNAL_NOTIFICATION_QUEUE = "Comment Internal Notification Queue";
    public static final String NAME_EMAIL_NOTIFICATION_QUEUE = "Comment Email Notification Queue";
    public static final String NAME_COMMENT_NOTIFICATION_FANOUT_EXCHANGE = "Comment Notification Fanout Exchange";
    public static final String NAME_COMMENT_NOTIFICATION_DIRECT_EXCHANGE = "Comment Notification Direct Exchange";
}
