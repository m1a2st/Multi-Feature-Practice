package com.example.spring.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Aspect
@Component
@Order(1)
@RequiredArgsConstructor
public class TxTemplateTransactionAop {

    private final TransactionTemplate transactionTemplate;

    @Around(value = "@annotation(com.example.spring.annotation.transaction.TxTemplatePostgresTransaction)")
    public void afterThrowing(ProceedingJoinPoint proceedJointPoint) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    proceedJointPoint.proceed();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
