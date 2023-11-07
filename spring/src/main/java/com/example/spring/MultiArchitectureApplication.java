package com.example.spring;

import com.example.socketio.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.example.spring"})
@EnableConfigurationProperties({ApplicationProperties.class})
public class MultiArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiArchitectureApplication.class, args);
    }
}
