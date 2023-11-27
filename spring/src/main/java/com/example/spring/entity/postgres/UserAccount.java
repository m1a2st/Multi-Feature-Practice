package com.example.spring.entity.postgres;

import com.example.spring.entity.UserAccountListener;
import javax.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@EntityListeners(UserAccountListener.class)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_account")
@Entity
@Slf4j
@Builder
public class UserAccount {

    @Id
    @GeneratedValue
    private Integer id;

    private String userName;
    private String firstName;
    private String lastName;

    @Transient
    private String fullName;

    @PrePersist
    public void logNewUserAttempt() {
        System.out.println("Attempting to add new user with username: " + userName);
    }

    @PostPersist
    public void logNewUserAdded() {
        System.out.println("Added user '" + userName + "' with ID: " + id);
    }

    @PreRemove
    public void logUserRemovalAttempt() {
        System.out.println("Attempting to delete user: " + userName);
    }

    @PostRemove
    public void logUserRemoval() {
        System.out.println("Deleted user: " + userName);
    }

    @PreUpdate
    public void logUserUpdateAttempt() {
        System.out.println("Attempting to update user: " + userName);
    }

    @PostUpdate
    public void logUserUpdate() {
        System.out.println("Updated user: " + userName);
    }

    @PostLoad
    public void logUserLoad() {
        log.info("Loaded user: " + userName);
        fullName = firstName + " " + lastName;
    }
}
