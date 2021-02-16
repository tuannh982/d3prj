package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicyConditionVisitor;

public interface TransactionEventPolicyConditionVisitor<T extends TransactionEvent> extends EventPolicyConditionVisitor<T> {
}
