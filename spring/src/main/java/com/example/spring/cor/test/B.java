package com.example.spring.cor.test;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(1)
@Service
public class B extends AbstractIA {

    @Override
    protected void doSomethingElse() {
        System.out.println("B");
    }
}
