package com.example.spring.repository.postgres;


import com.example.spring.entity.postgres.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findByUserName(String userName);
}
