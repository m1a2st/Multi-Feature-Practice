package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackRollingExample {

    private static final Logger log = LoggerFactory.getLogger(LogBackRollingExample.class);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2000; i++) {
            log.info("This is the " + i + " time I say 'Hello World'.");
            Thread.sleep(1);
        }
    }
}
