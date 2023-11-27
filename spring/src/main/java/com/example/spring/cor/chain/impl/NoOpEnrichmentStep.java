package com.example.spring.cor.chain.impl;

import com.example.spring.cor.Message;
import com.example.spring.cor.chain.template.EnrichmentStep;

public class NoOpEnrichmentStep implements EnrichmentStep {

    @Override
    public void setNext(EnrichmentStep next) {}

    @Override
    public Message enrich(Message message) {
        return message;
    }
}
