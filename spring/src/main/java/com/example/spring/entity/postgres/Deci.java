package com.example.spring.entity.postgres;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "deci")
@Data
public class Deci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal one;
}
