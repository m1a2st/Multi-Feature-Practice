package com.example.spring.entity;

import com.example.spring.entity.postgres.UserAccount;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
public class UserAccountListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(UserAccount user) {
        if (user.getId() == 0) {
            log.info("[USER AUDIT] About to add a user");
        } else {
            log.info("[USER AUDIT] About to update/delete user: " + user.getId());
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(UserAccount user) {
        log.info("[USER AUDIT] add/update/delete complete for user: " + user.getId());
    }

    @PostLoad
    private void afterLoad(UserAccount user) {
        log.info("[USER AUDIT] user loaded from database: " + user.getId());
    }
}
