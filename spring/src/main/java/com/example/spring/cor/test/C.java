package com.example.spring.cor.test;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Order(2)
@Service
public class C extends AbstractIA {

    private InputStream inputStream;
    @Override
    protected void doSomethingElse() {
        System.out.println("C");
    }
}
