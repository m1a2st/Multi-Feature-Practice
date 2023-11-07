package com.example.spring.cor.chain.impl;


import com.example.spring.cor.Message;
import com.example.spring.cor.chain.template.EnrichmentStep;

import java.util.Optional;

public abstract class AbstractEnrichmentStep implements EnrichmentStep {

    private EnrichmentStep next;

    @Override
    public void setNext(EnrichmentStep step) {
        next = step;
    }

    @Override
    public Message enrich(Message message) {
        return enrichAndApplyNext(message)
                .map(enrichedMessage -> next.enrich(enrichedMessage))
                .orElseGet(() -> next.enrich(message));
    }

    protected abstract Optional<Message> enrichAndApplyNext(Message message);
}
