package com.example.spring.propmap.annotation;

import com.example.spring.propmap.models.PropMapGroup;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface PropMapBean {
    PropMapGroup group() default PropMapGroup.GENERAL;
}
