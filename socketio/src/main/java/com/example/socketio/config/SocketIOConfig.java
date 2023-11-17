package com.example.socketio.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {

    private final ApplicationProperties.Socketio config;

    public SocketIOConfig(ApplicationProperties applicationProperties) {
        this.config = applicationProperties.getSocketio();
    }

    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(this.config.getHost());
        config.setPort(this.config.getPort());
        return new SocketIOServer(config);
    }
}
