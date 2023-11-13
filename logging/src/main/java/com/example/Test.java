package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private final static Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        log.trace("Hello World! Test Trace");
        log.debug("Hello World! Test Debug");
        log.info("Hello World! Test Info");
        log.warn("Hello World! Test Warn");
        log.error("Hello World! Test Error");
    }
}
