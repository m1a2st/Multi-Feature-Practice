package com.example.spring.service;

import com.example.spring.annotation.transaction.MultipleSqlTransaction;
import com.example.spring.annotation.transaction.MysqlTransaction;
import com.example.spring.annotation.transaction.PostgresTransaction;
import com.example.spring.entity.mysql.Student;
import com.example.spring.entity.postgres.Classroom;
import com.example.spring.repository.mysql.StudentRepository;
import com.example.spring.repository.postgres.ClassRoomRepository;
import com.example.spring.repository.proxy.StudentRepositoryProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final StudentRepository studentRepository;
    private final ClassRoomRepository classRoomRepository;
    private final StudentRepositoryProxy studentRepositoryProxy;

    public void saveStudent(Student student) {
        System.out.println("save student in service");
        studentRepositoryProxy.saveStudent(student);
    }

    public void setClassroomAndStudent() {
        studentRepository.save(Student.builder()
                .name("student1")
                .age(10)
                .build());
        classRoomRepository.save(Classroom.builder()
                .name("classroom1")
                .build());
    }

    @PostgresTransaction
    public void batchInsertClassroom(){
        classRoomRepository.save(Classroom.builder()
                .name("classroom4")
                .build());
        if(System.currentTimeMillis() % 2 == 0){
            throw new RuntimeException("test");
        }
        classRoomRepository.save(Classroom.builder()
                .name("classroom5")
                .build());
    }

    @MysqlTransaction
    public void batchInsertStudent(){
        studentRepository.save(Student.builder()
                .name("student4")
                .age(14)
                .build());
        if(System.currentTimeMillis() % 2 == 0){
            throw new RuntimeException("test");
        }
        studentRepository.save(Student.builder()
                .name("student5")
                .age(15)
                .build());
    }

    @MultipleSqlTransaction
    public void batchInsertClassroomAndStudent(){
        classRoomRepository.save(Classroom.builder()
                .name("classroom6")
                .build());
        studentRepository.save(Student.builder()
                .name("student6")
                .age(16)
                .build());
        if(true){
            throw new RuntimeException("test");
        }
        classRoomRepository.save(Classroom.builder()
                .name("classroom7")
                .build());
        studentRepository.save(Student.builder()
                .name("student7")
                .age(17)
                .build());
    }
}
