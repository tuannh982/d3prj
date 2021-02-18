package com.tuannh.offer.management.domain.policy.event.transaction.chain;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.PolicyArgumentTransactionEventPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import lombok.NonNull;

import java.util.List;

public class ChainPolicy extends PolicyArgumentTransactionEventPolicy {
    public ChainPolicy(@NonNull TransactionEventPolicy[] policies) {
        super(policies);
    }

    public ChainPolicy(@NonNull List<TransactionEventPolicy> policies) {
        super(policies);
    }

    public ChainPolicy(int argc, @NonNull Object[] args) {
        super(argc, args);
    }

    public ChainPolicy(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
        super(argc, args, argsType);
    }

    @Override
    public Boolean handle(TransactionEvent data) {
        for (int i = 0; i < argc; i++) {
            final boolean b = policies.get(i).handle(data);
            if (!b) return false;
        }
        return true;
    }
}
