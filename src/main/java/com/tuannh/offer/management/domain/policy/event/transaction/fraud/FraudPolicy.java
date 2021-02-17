package com.tuannh.offer.management.domain.policy.event.transaction.fraud;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FraudPolicy extends TransactionEventPolicy<TransactionEvent, Boolean> {
    private static Set<String> bannedList = new HashSet<>(Arrays.asList(
            "TEST",
            "ANONYMOUS"
    )); // mock

    @Override
    public Boolean handle(TransactionEvent data) {
        return !bannedList.contains(data.getUserId());
    }
}
