package com.example.spring.models.request;

import com.example.spring.entity.postgres.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAccountRequest {

    private Integer Id;
    private String userName;
    private String firstName;
    private String lastName;

    public UserAccount toEntity() {
        return UserAccount.builder()
                .id(Id)
                .userName(userName)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
