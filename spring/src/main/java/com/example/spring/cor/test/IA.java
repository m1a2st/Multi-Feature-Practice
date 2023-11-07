package com.example.spring.cor.test;

import java.util.List;

public interface IA {

    void setNext(IA next);

    void doSomething();

    static IA buildChain(List<IA> elements, IA lastElement) {
        if (elements.isEmpty()) {
            return lastElement;
        }
        for (int i = 0; i < elements.size(); i++) {
            var current = elements.get(i);
            var next = i < elements.size() - 1 ? elements.get(i + 1) : lastElement;
            current.setNext(next);
        }
        return elements.get(0);
    }
}
