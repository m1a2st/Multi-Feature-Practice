package com.example.spring.entity.postgres;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "classroom")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classroom {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "name", length = 20)
    private String name;
}
