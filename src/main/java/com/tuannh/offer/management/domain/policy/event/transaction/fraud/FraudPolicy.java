package com.tuannh.offer.management.domain.policy.event.transaction.fraud;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.ConditionArgumentTransactionEventPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class FraudPolicy extends ConditionArgumentTransactionEventPolicy {
    @JsonIgnore
    private final List<TransactionEventPolicyCondition<TransactionEvent>> conditions;

    public FraudPolicy(int argc, @NonNull Object[] args) {
        super(argc, args);
        conditions = new ArrayList<>();
        for (int i = 0; i < argc; i++) {
            ConditionArgs temp = (ConditionArgs) args[i];
            conditions.add(FraudConditionFactory.of(
                    temp.getConditionName(),
                    temp.getArgc(),
                    temp.getArgs()
            ));
        }
    }

    @Override
    public Boolean handle(TransactionEvent data) {
        for (TransactionEventPolicyCondition<TransactionEvent> condition : conditions) {
            if (!condition.handle(data)) {
                return false;
            }
        }
        return true;
    }
}
