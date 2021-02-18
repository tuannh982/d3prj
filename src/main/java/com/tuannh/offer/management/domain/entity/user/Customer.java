package com.tuannh.offer.management.domain.entity.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Customer {
    private final String userId;
    @Setter
    private Map<String, Object> demographicData = null;

    public Customer(String userId) {
        this.userId = userId;
    }

    public Customer(String userId, Map<String, Object> demographicData) {
        this.userId = userId;
        this.demographicData = demographicData;
    }

    public static Customer init(String customerId) {
        return new Customer(customerId);
    }
}
