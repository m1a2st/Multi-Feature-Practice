package com.example.spring.cor.chain.impl;


import com.example.spring.cor.Message;
import com.example.spring.cor.repository.impl.AgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Order(1)
@RequiredArgsConstructor
public class AgeEnrichmentStep extends AbstractEnrichmentStep {

    private final AgeService ageService;

    @Override
    protected Optional<Message> enrichAndApplyNext(Message message) {
        return message.getValue("userId")
                .flatMap(ageService::findAgeByUserId)
                .map(age -> message.with("age", age + ""));
    }
}
