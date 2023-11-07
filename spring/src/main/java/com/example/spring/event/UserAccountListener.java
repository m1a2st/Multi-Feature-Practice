package com.example.spring.event;

import com.example.spring.entity.postgres.UserAccountChangeLog;
import com.example.spring.repository.postgres.UserAccountChangeLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAccountListener implements ApplicationListener<UserAccountEvent> {

    private final UserAccountChangeLogRepository repository;

    @Override
    public void onApplicationEvent(UserAccountEvent event) {
        UserAccountChangeLog changeLog = event.toChangeLog();
        log.info("UserAccountEvent: {}", changeLog);
        repository.save(changeLog);
    }
}
