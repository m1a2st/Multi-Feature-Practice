package com.example.spring.models;


import com.example.spring.annotation.bigdecimal.DefaultDecimalFormat;
import com.example.spring.annotation.bigdecimal.MoneyFormat;
import com.example.spring.annotation.bigdecimal.RateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BigDecimalData {

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime time;
    @DefaultDecimalFormat
    private BigDecimal one;
    @MoneyFormat
    private BigDecimal two;
    @RateFormat
    private BigDecimal three;
    @DefaultDecimalFormat
    private BigDecimal four;
    @MoneyFormat
    private BigDecimal five;
    @RateFormat
    private BigDecimal six;
}