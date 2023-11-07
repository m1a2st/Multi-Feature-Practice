package com.example.spring.cor.repository.impl;

import com.example.spring.cor.repository.AgeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgeService implements AgeRepository {
    @Override
    public Optional<Integer> findAgeByUserId(String userId) {
        return Optional.of(42);
    }
}
