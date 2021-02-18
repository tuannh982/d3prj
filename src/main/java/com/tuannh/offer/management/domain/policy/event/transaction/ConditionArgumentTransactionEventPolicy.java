package com.tuannh.offer.management.domain.policy.event.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuannh.offer.management.commons.util.ArrayUtils;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ConditionArgumentTransactionEventPolicy extends TransactionEventPolicy {
    @JsonIgnore
    protected final List<TransactionEventPolicyCondition> conditions;

    protected ConditionArgumentTransactionEventPolicy(@NonNull TransactionEventPolicyCondition[] conditions) {
        super(conditions.length, conditions, ArrayUtils.classArray(conditions.length, TransactionEventPolicyCondition.class));
        this.conditions = new ArrayList<>();
        this.conditions.addAll(Arrays.asList(conditions).subList(0, argc));
    }

    protected ConditionArgumentTransactionEventPolicy(@NonNull List<TransactionEventPolicyCondition> conditions) {
        super(conditions.size(), conditions.toArray(), ArrayUtils.classArray(conditions.size(), TransactionEventPolicyCondition.class));
        this.conditions = conditions;
    }

    protected ConditionArgumentTransactionEventPolicy(int argc, @NonNull Object[] args) {
        super(argc, args, ArrayUtils.classArray(argc, TransactionEventPolicy.class));
        conditions = new ArrayList<>(argc);
    }

    protected ConditionArgumentTransactionEventPolicy(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
        super(argc, args, argsType);
        conditions = new ArrayList<>(argc);
        for (int i = 0; i < argc; i++) {
            conditions.add((TransactionEventPolicyCondition) args[i]);
        }
    }
}
