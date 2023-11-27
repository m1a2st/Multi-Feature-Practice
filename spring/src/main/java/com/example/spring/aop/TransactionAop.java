package com.example.spring.aop;

import com.example.spring.common.TransactionControl;
import com.example.spring.common.TransactionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class TransactionAop {

    private final TransactionService postgresTransactionService;
    private final TransactionService mysqlTransactionService;

    public TransactionAop(
            @Qualifier("postgresTransactionService") TransactionService postgresTransactionService,
            @Qualifier("mysqlTransactionService") TransactionService mysqlTransactionService) {
        this.postgresTransactionService = postgresTransactionService;
        this.mysqlTransactionService = mysqlTransactionService;
    }

    @Around(value = "@annotation(com.example.spring.annotation.transaction.MultipleSqlTransaction)")
    public void afterThrowing(ProceedingJoinPoint proceedJointPoint) {
        TransactionControl mysqlTransactionControl = mysqlTransactionService.openTransaction();
        TransactionControl postgresTransactionControl = postgresTransactionService.openTransaction();
        try {
            proceedJointPoint.proceed();
            mysqlTransactionControl.commit();
            postgresTransactionControl.commit();
        } catch (Throwable e) {
            mysqlTransactionControl.rollback();
            postgresTransactionControl.rollback();
            throw new RuntimeException(e);
        }
    }
}
