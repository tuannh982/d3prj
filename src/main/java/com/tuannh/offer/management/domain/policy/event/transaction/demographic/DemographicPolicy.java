package com.tuannh.offer.management.domain.policy.event.transaction.demographic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.*;
import com.tuannh.offer.management.domain.policy.event.transaction.demographic.condition.DemographicConditionFactory;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DemographicPolicy extends ConditionArgumentTransactionEventPolicy {
    @JsonIgnore
    private final List<TransactionEventPolicyCondition<TransactionEvent>> conditions;

    public DemographicPolicy(int argc, @NonNull Object[] args) {
        super(argc, args);
        conditions = new ArrayList<>();
        for (int i = 0; i < argc; i++) {
            ConditionArgs temp = (ConditionArgs) args[i];
            conditions.add(DemographicConditionFactory.of(
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
