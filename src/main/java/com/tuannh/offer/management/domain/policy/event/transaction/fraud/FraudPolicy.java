package com.tuannh.offer.management.domain.policy.event.transaction.fraud;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionPolicyFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class FraudPolicy extends TransactionEventPolicy<TransactionEvent, Boolean> {
    private final List<TransactionEventPolicyCondition<TransactionEvent>> conditions;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ConditionArgs {
        private String conditionName;
        private int argc;
        private Object[] args;
    }

    public FraudPolicy(int argc, @NonNull Object[] args) {
        super(argc, args);
        conditions = new ArrayList<>();
        for (int i = 0; i < argc; i++) {
            FraudPolicy.ConditionArgs temp = (FraudPolicy.ConditionArgs) args[i];
            conditions.add(FraudConditionFactory.of(
                    temp.conditionName,
                    temp.argc,
                    temp.args
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
