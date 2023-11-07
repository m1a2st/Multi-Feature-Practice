package com.example.spring.annotation.bigdecimal;

import com.example.spring.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonFormat(pattern = "###,###,###")
@JsonSerialize(using = BigDecimalSerializer.class)
public @interface MoneyFormat {

}
