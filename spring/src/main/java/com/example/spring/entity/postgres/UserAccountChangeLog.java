package com.example.spring.entity.postgres;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_account_change_log")
@Entity
@Builder
public class UserAccountChangeLog {

    @Id
    private String uuid;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "old_user_name")
    private String oldUserName;
    @Column(name = "old_first_name")
    private String oldFirstName;
    @Column(name = "old_last_name")
    private String oldLastName;
    @Column(name = "new_user_name")
    private String newUserName;
    @Column(name = "new_first_name")
    private String newFirstName;
    @Column(name = "new_last_name")
    private String newFastName;
}
