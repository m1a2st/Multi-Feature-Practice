package com.example.spring.cor.repository.impl;

import com.example.spring.cor.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneNumberService implements PhoneNumberRepository {

    @Override
    public Optional<String> findPhoneNumber(String sessionId) {
        return Optional.of("123456789");
    }
}
