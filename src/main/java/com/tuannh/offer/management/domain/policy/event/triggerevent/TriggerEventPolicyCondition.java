package com.tuannh.offer.management.domain.policy.event.triggerevent;

import com.tuannh.offer.management.domain.event.TriggerEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicyCondition;

public interface TriggerEventPolicyCondition<T extends TriggerEvent> extends EventPolicyCondition<T> {
}