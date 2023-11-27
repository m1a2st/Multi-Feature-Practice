package com.example.spring.entity.mysql;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "age")
    private Integer age;
}
