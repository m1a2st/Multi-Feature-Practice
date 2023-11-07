package com.example.spring.cor.repository;

import java.util.Optional;

public interface PhoneNumberRepository {
    Optional<String> findPhoneNumber(String sessionId);
}
