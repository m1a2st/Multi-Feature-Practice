package com.example.spring.annotation.transaction;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

@Target({METHOD})
@Retention(RUNTIME)
@Transactional
@Documented
public @interface PostgresTransaction {

    @AliasFor(annotation = Transactional.class, attribute = "value")
    String value() default "postgresTransactionManager";
}
