package com.example.spring.models;

import com.example.spring.annotation.bigdecimal.DefaultDecimalFormat;
import com.example.spring.annotation.bigdecimal.MoneyFormat;
import com.example.spring.annotation.bigdecimal.RateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BigDecimalData {

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime time;

    @DefaultDecimalFormat
    private BigDecimal one;

    @MoneyFormat(negative = true)
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
