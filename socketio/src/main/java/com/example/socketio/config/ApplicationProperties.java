package com.example.socketio.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private final Socketio socketio = new Socketio();

    @Setter
    @Getter
    public static class Socketio{
        private String host;
        private int port;
        private boolean active;
    }
}
