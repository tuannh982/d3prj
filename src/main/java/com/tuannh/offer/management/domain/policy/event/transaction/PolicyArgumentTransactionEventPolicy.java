package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

public abstract class PolicyArgumentTransactionEventPolicy extends TransactionEventPolicy<TransactionEvent, Boolean> {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class PolicyArgs {
        private String policyName;
        private int argc;
        private Object[] args;
    }

    protected PolicyArgumentTransactionEventPolicy(int argc, @NonNull Object[] args) {
        super(argc, args);
    }
}
