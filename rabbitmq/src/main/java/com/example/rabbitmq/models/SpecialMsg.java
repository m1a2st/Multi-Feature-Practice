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
public class SpecialMsg implements Serializable {
    private String content;
    private Mode mode;
    private String routingKey;

    public enum Mode {
        FANOUT,
        DIRECT
    }
}
