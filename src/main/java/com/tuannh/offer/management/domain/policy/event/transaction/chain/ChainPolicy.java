package com.tuannh.offer.management.domain.policy.event.transaction.chain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.PolicyArgumentTransactionEventPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionPolicyFactory;
import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChainPolicy extends PolicyArgumentTransactionEventPolicy {
    @JsonIgnore
    private final List<TransactionEventPolicy> policies;

    public ChainPolicy(@NonNull TransactionEventPolicy[] policies) {
        super(policies);
        this.policies = new ArrayList<>();
        this.policies.addAll(Arrays.asList(policies).subList(0, argc));
    }

    public ChainPolicy(@NonNull List<TransactionEventPolicy> policies) {
        super(policies);
        this.policies = policies;
    }

    public ChainPolicy(int argc, @NonNull Object[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(argc, args); // error should be occurred here
        policies = new ArrayList<>();
        for (int i = 0; i < argc; i++) {
            PolicyArgs temp = (PolicyArgs) args[i];
            policies.add(TransactionPolicyFactory.of(
                    temp.getPolicyName(),
                    temp.getArgc(),
                    temp.getArgs()
            ));
        }
    }

    @Override
    public Boolean handle(TransactionEvent data) {
        for (int i = 0; i < argc; i++) {
            final boolean b = policies.get(i).handle(data);
            if (!b) return false;
        }
        return true;
    }
}
