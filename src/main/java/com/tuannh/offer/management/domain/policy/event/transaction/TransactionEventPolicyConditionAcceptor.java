package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicyConditionAcceptor;

public interface TransactionEventPolicyConditionAcceptor<T extends TransactionEvent> extends EventPolicyConditionAcceptor<T> {
}