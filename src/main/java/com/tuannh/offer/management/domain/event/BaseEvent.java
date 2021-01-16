package com.tuannh.offer.management.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BaseEvent {
    private final String eventName;
    private final long ts;
}
