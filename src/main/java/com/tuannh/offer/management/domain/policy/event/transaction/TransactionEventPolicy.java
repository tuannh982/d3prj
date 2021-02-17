package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.commons.assertion.Assertions;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicy;
import lombok.NonNull;

public abstract class TransactionEventPolicy<T extends TransactionEvent, R extends Boolean> extends EventPolicy<T, R> {
    protected int argc;
    protected Object[] args;

    protected TransactionEventPolicy(int argc, @NonNull Object[] args) {
        Assertions.equalChecks(argc, args.length);
        this.argc = argc;
        this.args = args;
    }
}
