package com.tuannh.offer.management.domain.policy.event.transaction.demographic;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.*;
import lombok.NonNull;

import java.util.List;

public class DemographicPolicy extends ConditionArgumentTransactionEventPolicy {
    public DemographicPolicy(@NonNull TransactionEventPolicyCondition[] conditions) {
        super(conditions);
    }

    public DemographicPolicy(@NonNull List<TransactionEventPolicyCondition> conditions) {
        super(conditions);
    }

    public DemographicPolicy(int argc, @NonNull Object[] args) {
        super(argc, args);
    }

    public DemographicPolicy(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
        super(argc, args, argsType);
    }

    @Override
    public Boolean handle(TransactionEvent data) {
        for (TransactionEventPolicyCondition condition : conditions) {
            if (!condition.handle(data)) {
                return false;
            }
        }
        return true;
    }
}
