package com.example.spring.repository.postgres;


import com.example.spring.entity.postgres.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<Classroom, Integer> {
}
