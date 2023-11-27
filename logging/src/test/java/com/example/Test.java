package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class Test {

    private static final Logger log = LoggerFactory.getLogger(Test.class);
    private static final Marker TEST_MARKER = MarkerFactory.getMarker("TEST");

    public static void main(String[] args) {
        log.trace("Hello World! Test Trace");
        log.debug("Hello World! Test Debug");
        log.info("Hello World! Test Info");
        log.warn("Hello World! Test Warn");
        log.error(TEST_MARKER, "Hello World! Test Error");
    }
}
