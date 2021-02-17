package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

public abstract class ConditionArgumentTransactionEventPolicy extends TransactionEventPolicy<TransactionEvent, Boolean> {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ConditionArgs {
        private String conditionName;
        private int argc;
        private Object[] args;
    }

    protected ConditionArgumentTransactionEventPolicy(int argc, @NonNull Object[] args) {
        super(argc, args);
    }
}
