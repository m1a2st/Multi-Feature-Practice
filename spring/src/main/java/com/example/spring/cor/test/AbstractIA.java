package com.example.spring.cor.test;

public abstract class AbstractIA implements IA {

    private IA next;

    @Override
    public void setNext(IA next) {
        this.next = next;
    }

    @Override
    public void doSomething() {
        doSomethingElse();
        if (next != null) {
            next.doSomething();
        }
    }

    protected abstract void doSomethingElse();
}
