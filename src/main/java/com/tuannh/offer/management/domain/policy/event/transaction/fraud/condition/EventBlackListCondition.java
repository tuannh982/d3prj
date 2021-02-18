package com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;

import java.util.List;

public class EventBlackListCondition implements TransactionEventPolicyCondition<TransactionEvent> {
    private final List<String> bannedList;

    public EventBlackListCondition(List<String> bannedList) {
        this.bannedList = bannedList;
    }

    @Override
    public boolean handle(TransactionEvent data) {
        return !bannedList.contains(data.getEventName());
    }
}
