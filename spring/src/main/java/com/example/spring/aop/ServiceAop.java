package com.example.spring.aop;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ServiceAop {

    @Around("@annotation(com.example.spring.aop.ServiceAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            System.out.println("In Before Around");
            Arrays.stream(args).forEach(System.out::println);
            System.out.println("Out Before Around");
        }

        System.out.println("Before ServiceAop");

        Object proceed = joinPoint.proceed();

        System.out.println("After ServiceAop");

        if (proceed != null) {
            System.out.println("In After Around");
            System.out.println(proceed);
            System.out.println("Out After Around");
        }

        return proceed;
    }

    @Pointcut("execution(* com.example.spring.service.*.*(..))")
    public void servicePointcut() {}

    @Before("servicePointcut()")
    public void beforeService() {
        System.out.println("Before ServiceAop BBBBBBBBB");
    }

    @After("servicePointcut()")
    public void afterService() {
        System.out.println("After ServiceAop AAAAAAAAAA");
    }
}
