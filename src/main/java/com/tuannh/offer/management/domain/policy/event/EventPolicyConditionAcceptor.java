package com.tuannh.offer.management.domain.policy.event;

import com.tuannh.offer.management.domain.event.DomainEvent;
import com.tuannh.offer.management.domain.policy.PolicyConditionAcceptor;

public interface EventPolicyConditionAcceptor<T extends DomainEvent> extends PolicyConditionAcceptor<T> {
}