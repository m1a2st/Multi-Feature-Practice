package com.example.spring.common;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TransactionControlBag {

    private final List<TransactionControl> controls = new ArrayList<>();
    private final PlatformTransactionManager ptm;

    public TransactionControlBag(PlatformTransactionManager platformTransactionManager) {
        this.ptm = platformTransactionManager;
    }

    public void keep(TransactionControl control) {
        controls.add(control);
    }

    public void tryRollbackAll() {
        for (TransactionControl control : controls) {
            if (control == null) {
                continue;
            }
            try {
                TransactionStatus status = control.getStatus();
                if (!status.isCompleted() || status.isRollbackOnly()) {
                    ptm.rollback(status);
                }
            } catch (Exception ignored) {
            }
        }
    }
}
