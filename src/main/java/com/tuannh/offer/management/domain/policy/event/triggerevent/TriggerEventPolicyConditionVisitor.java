package com.tuannh.offer.management.domain.policy.event.triggerevent;

import com.tuannh.offer.management.domain.event.TriggerEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicyConditionVisitor;

public interface TriggerEventPolicyConditionVisitor<T extends TriggerEvent> extends EventPolicyConditionVisitor<T> {
}
