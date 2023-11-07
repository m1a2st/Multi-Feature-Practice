package com.example.spring.common;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionControl {

    private final PlatformTransactionManager ptm;
    private final TransactionStatus status;

    public TransactionControl(PlatformTransactionManager ptm) {
        this.ptm = ptm;
        status = ptm.getTransaction(new DefaultTransactionDefinition());
    }

    public void commit() {
        if (!status.isCompleted()) {
            ptm.commit(status);
        }
    }

    public void rollback() {
        if (!status.isCompleted()) {
            ptm.rollback(status);
        }
    }

    protected TransactionStatus getStatus() {
        return status;
    }
}
