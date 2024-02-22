package com.example.spring.converter;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class TransferAspect {

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) && ")
    public void cutMethod(JoinPoint joinPoint) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("cutMethod");
        joinPoint.getSignature();
        for (Object arg : joinPoint.getArgs()) {
            System.out.println(arg);
            Class<?> clazz = arg.getClass();
            for (Field declaredField : clazz.getDeclaredFields()) {
                Annotation[] annotations = declaredField.getAnnotations();
                if (declaredField.isAnnotationPresent(TransferProperty.class)) {
                    System.out.println(declaredField.getName());
                    Method method = clazz.getMethod("set" + declaredField.getName().substring(0, 1).toUpperCase() + declaredField.getName().substring(1), String.class);
                    method.invoke(arg,"123456");
                }
            }
        }
        System.out.println(joinPoint.getSignature().getName());
    }
}
