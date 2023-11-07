package com.example.spring.cor.chain.facade;


import com.example.spring.cor.Message;
import com.example.spring.cor.chain.impl.NoOpEnrichmentStep;
import com.example.spring.cor.chain.template.EnrichmentStep;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.spring.cor.chain.template.ChainElement.buildChain;


@Service
public class EnrichmentStepFacade {

    private final EnrichmentStep chainHead;

    public EnrichmentStepFacade(List<EnrichmentStep> steps) {
        this.chainHead = buildChain(steps, new NoOpEnrichmentStep());
    }

    public Message enrich(Message message) {
        return chainHead.enrich(message);
    }
}
