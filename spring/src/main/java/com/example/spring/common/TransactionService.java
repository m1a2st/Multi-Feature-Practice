package com.example.spring.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service
@Scope("Singleton")
@RequiredArgsConstructor
public class TransactionService {

    private final PlatformTransactionManager platformTransactionManager;
    private final TransactionControlBag controlBag;

    public TransactionControl openTransaction() {
        TransactionControl transactionControl = new TransactionControl(platformTransactionManager);
        controlBag.keep(transactionControl);
        return transactionControl;
    }
}
