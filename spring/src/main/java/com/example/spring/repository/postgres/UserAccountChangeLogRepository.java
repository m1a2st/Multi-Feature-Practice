package com.example.spring.repository.postgres;


import com.example.spring.entity.postgres.UserAccountChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountChangeLogRepository extends JpaRepository<UserAccountChangeLog, String> {
}
