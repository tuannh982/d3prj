package com.tuannh.offer.management.domain.entity.customer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Customer {
    private Profile profile;
    private Map<String, Object> properties;
}
