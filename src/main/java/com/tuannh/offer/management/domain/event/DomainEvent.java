package com.tuannh.offer.management.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class DomainEvent {
    private final String eventName;
}
