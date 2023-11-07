package com.example.spring.cor.repository;

import java.util.Optional;

public interface AgeRepository {
    Optional<Integer> findAgeByUserId(String userId);
}
