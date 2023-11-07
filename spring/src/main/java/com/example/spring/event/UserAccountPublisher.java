package com.example.spring.event;

import com.example.spring.entity.postgres.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAccountPublisher {

    private final ApplicationEventPublisher publisher;

    public void publishEvent(UserAccount newUserAccount) {
        log.debug("Publishing custom event. ");
        publisher.publishEvent(new UserAccountEvent(this,
                newUserAccount.getId(),
                newUserAccount.getUserName(),
                newUserAccount.getFirstName(),
                newUserAccount.getLastName()
        ));
    }

    public void publishEvent(UserAccount oldUserAccount, UserAccount newUserAccount) {
        log.debug("Publishing custom event. ");
        publisher.publishEvent(new UserAccountEvent(this,
                oldUserAccount.getId(),
                oldUserAccount.getUserName(),
                oldUserAccount.getFirstName(),
                oldUserAccount.getLastName(),
                newUserAccount.getUserName(),
                newUserAccount.getFirstName(),
                newUserAccount.getLastName()
        ));
    }
}
