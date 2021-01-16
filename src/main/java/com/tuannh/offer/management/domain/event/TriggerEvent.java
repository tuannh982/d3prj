package com.tuannh.offer.management.domain.event;

import com.tuannh.offer.management.domain.entity.customer.Profile;
import lombok.Getter;

import java.util.Map;

@Getter
public class TriggerEvent extends DomainEvent {
    private final Profile user;
    private final Map<String, Object> properties;

    public TriggerEvent(String eventName, Profile user, Map<String, Object> properties, long ts) {
        super(eventName, ts);
        this.user = user;
        this.properties = properties;
    }
}
