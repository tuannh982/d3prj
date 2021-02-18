package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.commons.args.ArgsClass;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicyCondition;
import lombok.NonNull;

public abstract class TransactionEventPolicyCondition extends ArgsClass implements EventPolicyCondition<TransactionEvent> {
    protected TransactionEventPolicyCondition(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
        super(argc, args, argsType);
    }
}