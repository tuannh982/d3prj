package com.tuannh.offer.management.domain.policy.event.transaction.fraud;

import com.tuannh.offer.management.commons.assertion.Assertions;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import lombok.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Evaluating by using a blacklist of user IDs (just simple)
 */
@SuppressWarnings("unchecked")
public class FraudPolicy extends TransactionEventPolicy<TransactionEvent, Boolean> {
    private final Set<String> bannedList;

    public FraudPolicy(int argc, @NonNull Object[] argv) {
        super(argc, argv);
        Assertions.equalChecks(argc, 1);
        List<String> casted = (List<String>) argv[0];
        bannedList = new HashSet<>(casted);
    }

    @Override
    public Boolean handle(TransactionEvent data) {
        return !bannedList.contains(data.getUserId());
    }
}
