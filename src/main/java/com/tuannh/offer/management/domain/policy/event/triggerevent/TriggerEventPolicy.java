package com.tuannh.offer.management.domain.policy.event.triggerevent;

import com.tuannh.offer.management.domain.entity.reward.Offer;
import com.tuannh.offer.management.domain.event.TriggerEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicy;

public abstract class TriggerEventPolicy<T extends TriggerEvent, R extends Offer> extends EventPolicy<T, R> {
}
