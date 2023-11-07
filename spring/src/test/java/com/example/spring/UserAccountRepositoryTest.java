package com.example.spring;

import com.example.spring.entity.postgres.UserAccount;
import com.example.spring.repository.postgres.UserAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class UserAccountRepositoryTest {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Test
    public void test() {
        System.out.println(userAccountRepository.findByUserName("test"));
    }

    @Test
    public void insert(){
        UserAccount userAccount = new UserAccount();
        userAccount.setId(4);
        userAccount.setUserName("test4");
        userAccount.setFirstName("testFirst4");
        userAccount.setLastName("testLast4");
        userAccountRepository.save(userAccount);
    }
}
