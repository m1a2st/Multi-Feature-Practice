package com.example.rabbitmq.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentMsg implements Serializable {

    private int postId;
    private int creatorId;
    private String content;
}
