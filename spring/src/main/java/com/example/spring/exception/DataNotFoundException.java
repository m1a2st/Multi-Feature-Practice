package com.example.spring.exception;

public class DataNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Data not found";

    public DataNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
