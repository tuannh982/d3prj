package com.tuannh.offer.management.domain.policy.event.transaction.fraud;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.ConditionArgumentTransactionEventPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import lombok.NonNull;
import java.util.List;

public class FraudPolicy extends ConditionArgumentTransactionEventPolicy {
    public FraudPolicy(@NonNull TransactionEventPolicyCondition[] conditions) {
        super(conditions);
    }

    public FraudPolicy(@NonNull List<TransactionEventPolicyCondition> conditions) {
        super(conditions);
    }

    public FraudPolicy(int argc, @NonNull Object[] args) {
        super(argc, args);
    }

    public FraudPolicy(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
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
