package com.tuannh.offer.management.domain.policy.event.triggerevent;

import com.tuannh.offer.management.domain.event.TriggerEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicyConditionAcceptor;

public interface TriggerEventPolicyConditionAcceptor<T extends TriggerEvent> extends EventPolicyConditionAcceptor<T> {
}