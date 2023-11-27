package com.example.rabbitmq.consumer;

import com.example.rabbitmq.models.CommentMsg;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @RabbitListener(queues = "hello")
    public void processMsg(@Payload CommentMsg comment) {
        log.info("{} receives message successfully.", getClass().getSimpleName());

        // 模擬從 DB 取得文章標題和留言者名字
        String userName = getUserName(comment.getCreatorId());
        String postTitle = getPostTitle(comment.getPostId());

        // 模擬發送通知
        log.info("站內通知：{}在文章「{}」上留言", userName, postTitle);
        log.info("信件主旨：文章「{}」有新留言", postTitle);
        log.info("信件內容：{}在「{}」上留言：{}", userName, postTitle, comment.getContent());
    }

    // 以 Map 代替真實 DB
    private final Map<Integer, String> userNameMap = Map.of(1, "Vincent", 2, "Dora");
    private final Map<Integer, String> postNameMap = Map.of(1, "2023 週年慶", 2, "iPhone 15 週邊上架");

    private String getUserName(int userId) {
        return userNameMap.get(userId);
    }

    private String getPostTitle(int itemId) {
        return postNameMap.get(itemId);
    }
}
