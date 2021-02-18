package com.tuannh.offer.management.domain.policy.event.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PolicyArgumentTransactionEventPolicy extends TransactionEventPolicy {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class PolicyArgs {
        private String policyName;
        private int argc;
        private Object[] args;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class PolicyJsonArgs {
        private String policyName;
        private int argc;
        private JsonNode args;
    }

    @JsonIgnore
    protected final List<TransactionEventPolicy> policies;

    protected PolicyArgumentTransactionEventPolicy(@NonNull TransactionEventPolicy[] policies) {
        super(policies.length, policies);
        this.policies = new ArrayList<>();
        this.policies.addAll(Arrays.asList(policies).subList(0, argc));
    }

    protected PolicyArgumentTransactionEventPolicy(@NonNull List<TransactionEventPolicy> policies) {
        super(policies.size(), policies.toArray());
        this.policies = policies;
    }

    protected PolicyArgumentTransactionEventPolicy(int argc, @NonNull Object[] args) {
        super(argc, args);
        policies = new ArrayList<>(argc);
    }
}
