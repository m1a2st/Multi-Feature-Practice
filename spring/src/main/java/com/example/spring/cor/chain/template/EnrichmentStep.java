package com.example.spring.cor.chain.template;

import com.example.spring.cor.Message;

public interface EnrichmentStep extends ChainElement<EnrichmentStep> {
    Message enrich(Message message);
}
