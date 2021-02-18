package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.commons.args.ArgsClass;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicy;
import lombok.NonNull;

@SuppressWarnings("rawtypes")
public abstract class TransactionEventPolicy extends ArgsClass implements EventPolicy<TransactionEvent, Boolean> {
    protected TransactionEventPolicy(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
        super(argc, args, argsType);
    }
}
