package com.example.spring;

import com.example.socketio.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.example.spring"})
@EnableConfigurationProperties({ApplicationProperties.class})
@ComponentScan(basePackages = {"com.example.spring", "com.example.socketio", "com.example.rabbitmq"})
public class MultiArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiArchitectureApplication.class, args);
    }
}
