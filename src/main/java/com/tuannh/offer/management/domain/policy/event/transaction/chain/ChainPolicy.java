package com.tuannh.offer.management.domain.policy.event.transaction.chain;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionPolicyFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ChainPolicy extends TransactionEventPolicy<TransactionEvent, Boolean> {
    private final List<TransactionEventPolicy<TransactionEvent, Boolean>> policies;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class PolicyArgs {
        private String policyName;
        private int argc;
        private Object[] args;
    }

    public ChainPolicy(int argc, @NonNull Object[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(argc, args); // error should be occurred here
        policies = new ArrayList<>();
        for (int i = 0; i < argc; i++) {
            PolicyArgs temp = (PolicyArgs) args[i];
            policies.add(TransactionPolicyFactory.of(
                    temp.policyName,
                    temp.argc,
                    temp.args
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
