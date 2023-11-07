package com.example.spring.repository.postgres;


import com.example.spring.entity.postgres.Deci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeciRepository extends JpaRepository<Deci, Integer> {
}
