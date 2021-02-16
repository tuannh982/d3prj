package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicyCondition;

public interface TransactionEventPolicyCondition<T extends TransactionEvent> extends EventPolicyCondition<T> {
}