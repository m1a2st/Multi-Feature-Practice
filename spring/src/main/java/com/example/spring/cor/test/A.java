package com.example.spring.cor.test;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(0)
@Service
public class A extends AbstractIA {

    @Override
    protected void doSomethingElse() {
        System.out.println("A");
    }
}
