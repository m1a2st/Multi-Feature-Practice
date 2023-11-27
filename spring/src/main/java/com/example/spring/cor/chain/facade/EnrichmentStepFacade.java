package com.example.spring.cor.chain.facade;

import static com.example.spring.cor.chain.template.ChainElement.buildChain;

import com.example.spring.cor.Message;
import com.example.spring.cor.chain.impl.NoOpEnrichmentStep;
import com.example.spring.cor.chain.template.EnrichmentStep;
import java.util.List;
import org.springframework.stereotype.Service;

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
