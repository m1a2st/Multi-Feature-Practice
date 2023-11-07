package com.example.spring.service;

import com.example.spring.entity.postgres.UserAccount;
import com.example.spring.event.UserAccountPublisher;
import com.example.spring.exception.DataNotFoundException;
import com.example.spring.models.request.UserAccountRequest;
import com.example.spring.repository.postgres.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository repository;
    private final UserAccountPublisher publisher;

    public void create(UserAccountRequest request) {
        repository.save(request.toEntity());
        publisher.publishEvent(request.toEntity());
    }

    public void update(UserAccountRequest request) {
        UserAccount oldUserAccount = repository.findById(request.getId()).orElseThrow(DataNotFoundException::new);
        repository.save(request.toEntity());
        publisher.publishEvent(oldUserAccount, request.toEntity());
    }
}
