package com.tuannh.offer.management.domain.policy.event;

import com.tuannh.offer.management.domain.event.DomainEvent;
import com.tuannh.offer.management.domain.policy.PolicyConditionVisitor;

public interface EventPolicyConditionVisitor<T extends DomainEvent> extends PolicyConditionVisitor<T> {
}