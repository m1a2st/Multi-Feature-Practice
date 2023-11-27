package com.example.spring.cor.repository.impl;

import com.example.spring.cor.repository.PhoneNumberRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService implements PhoneNumberRepository {

    @Override
    public Optional<String> findPhoneNumber(String sessionId) {
        return Optional.of("123456789");
    }
}
