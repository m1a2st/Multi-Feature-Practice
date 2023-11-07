package com.example.spring.repository.proxy;

import com.example.spring.entity.mysql.Student;
import com.example.spring.repository.mysql.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentProxyImpl implements StudentRepositoryProxy {

    private final StudentRepository studentRepository;

    @Override
    public void saveStudent(Student student) {
        System.out.println("save student in proxy");
        studentRepository.save(student);
    }
}
