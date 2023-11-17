package com.example.rabbitmq.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentMsg implements Serializable {

    private int postId;
    private int creatorId;
    private String content;
}
