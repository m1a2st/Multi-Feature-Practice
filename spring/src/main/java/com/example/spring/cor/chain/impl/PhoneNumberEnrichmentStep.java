package com.example.spring.cor.chain.impl;


import com.example.spring.cor.Message;
import com.example.spring.cor.repository.impl.PhoneNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Order(0)
@RequiredArgsConstructor
public class PhoneNumberEnrichmentStep extends AbstractEnrichmentStep {

    private final PhoneNumberService phoneNumberService;

    @Override
    protected Optional<Message> enrichAndApplyNext(Message message) {
        return message.getValue("SESSIONID")
                .flatMap(phoneNumberService::findPhoneNumber)
                .map(phoneNumber -> message.with("phoneNumber", phoneNumber));
    }
}
