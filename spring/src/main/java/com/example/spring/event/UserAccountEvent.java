package com.example.spring.event;

import static java.util.UUID.randomUUID;

import com.example.spring.entity.postgres.UserAccountChangeLog;
import java.time.Clock;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@ToString
public class UserAccountEvent extends ApplicationEvent {

    private Integer id;
    private String oldUserName;
    private String oldFirstName;
    private String oldLastName;
    private String newUserName;
    private String newFirstName;
    private String newFastName;

    public UserAccountEvent(Object source, Integer id, String newUserName, String newFirstName, String newFastName) {
        super(source);
        this.id = id;
        this.newUserName = newUserName;
        this.newFirstName = newFirstName;
        this.newFastName = newFastName;
    }

    public UserAccountEvent(
            Object source,
            Integer id,
            String oldUserName,
            String oldFirstName,
            String oldLastName,
            String newUserName,
            String newFirstName,
            String newFastName) {
        super(source);
        this.id = id;
        this.oldUserName = oldUserName;
        this.oldFirstName = oldFirstName;
        this.oldLastName = oldLastName;
        this.newUserName = newUserName;
        this.newFirstName = newFirstName;
        this.newFastName = newFastName;
    }

    public UserAccountEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public UserAccountChangeLog toChangeLog() {
        return UserAccountChangeLog.builder()
                .uuid(randomUUID().toString())
                .userId(id)
                .oldUserName(oldUserName)
                .oldFirstName(oldFirstName)
                .oldLastName(oldLastName)
                .newUserName(newUserName)
                .newFirstName(newFirstName)
                .newFastName(newFastName)
                .build();
    }
}
