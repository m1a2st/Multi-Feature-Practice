package com.example.spring.repository.mysql;


import com.example.spring.entity.mysql.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
