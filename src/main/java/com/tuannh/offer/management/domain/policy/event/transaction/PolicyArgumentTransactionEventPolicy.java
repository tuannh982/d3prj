package com.tuannh.offer.management.domain.policy.event.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuannh.offer.management.commons.util.ArrayUtils;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PolicyArgumentTransactionEventPolicy extends TransactionEventPolicy {
    @JsonIgnore
    protected final List<TransactionEventPolicy> policies;

    protected PolicyArgumentTransactionEventPolicy(@NonNull TransactionEventPolicy[] policies) {
        super(policies.length, policies, ArrayUtils.classArray(policies.length, TransactionEventPolicy.class));
        this.policies = new ArrayList<>();
        this.policies.addAll(Arrays.asList(policies).subList(0, argc));
    }

    protected PolicyArgumentTransactionEventPolicy(@NonNull List<TransactionEventPolicy> policies) {
        super(policies.size(), policies.toArray(), ArrayUtils.classArray(policies.size(), TransactionEventPolicy.class));
        this.policies = policies;
    }

    protected PolicyArgumentTransactionEventPolicy(int argc, @NonNull Object[] args) {
        super(argc, args, ArrayUtils.classArray(argc, TransactionEventPolicy.class));
        policies = new ArrayList<>(argc);
    }

    protected PolicyArgumentTransactionEventPolicy(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
        super(argc, args, argsType);
        policies = new ArrayList<>(argc);
        for (int i = 0; i < argc; i++) {
            policies.add((TransactionEventPolicy) args[i]);
        }
    }
}
