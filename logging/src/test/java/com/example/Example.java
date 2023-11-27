package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {

    private static final Logger log = LoggerFactory.getLogger(Example.class);
    private static final ch.qos.logback.classic.Logger parentLogger =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        log.info("Hello World!");
        parentLogger.setLevel(ch.qos.logback.classic.Level.INFO);
        parentLogger.warn("This message is logged because WARN > INFO.");
        parentLogger.debug("This message is not logged because DEBUG < INFO.");
        log.info("INFO == INFO");
        log.debug("DEBUG < INFO");
    }
}
