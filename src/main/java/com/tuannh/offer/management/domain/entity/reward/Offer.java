package com.tuannh.offer.management.domain.entity.reward;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public abstract class Offer {
    private final String content;
}
