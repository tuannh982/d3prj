package com.tuannh.offer.management.domain.policy.event;

import com.tuannh.offer.management.domain.event.DomainEvent;
import com.tuannh.offer.management.domain.policy.PolicyCondition;

public interface EventPolicyCondition<T extends DomainEvent> extends PolicyCondition<T> {
}