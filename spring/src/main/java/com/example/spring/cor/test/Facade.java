package com.example.spring.cor.test;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Facade {

    private final IA head;

    public Facade(List<IA> ias) {
        this.head = ias.get(0);
    }

    public void doSomething() {
        head.doSomething();
    }
}
