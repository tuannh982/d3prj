package com.tuannh.offer.management.domain.policy.event;

import com.tuannh.offer.management.domain.event.DomainEvent;
import com.tuannh.offer.management.domain.policy.Policy;

public abstract class EventPolicy<T extends DomainEvent, R> extends Policy<T, R> {
}