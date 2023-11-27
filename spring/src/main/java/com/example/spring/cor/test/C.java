package com.example.spring.cor.test;

import java.io.InputStream;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(2)
@Service
public class C extends AbstractIA {

    private InputStream inputStream;

    @Override
    protected void doSomethingElse() {
        System.out.println("C");
    }
}
