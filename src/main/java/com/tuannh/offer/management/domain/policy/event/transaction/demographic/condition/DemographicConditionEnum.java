package com.tuannh.offer.management.domain.policy.event.transaction.demographic.condition;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum DemographicConditionEnum {
    CUSTOMER_PROPERTIES("customer_props", CustomerPropertiesCondition.class);
    private final String value;
    private final Class<? extends TransactionEventPolicyCondition<TransactionEvent>> clazz;

    private static final Map<String, DemographicConditionEnum> mp;
    static {
        mp = new HashMap<>();
        for (DemographicConditionEnum transactionPolicyEnum : DemographicConditionEnum.values()) {
            mp.put(transactionPolicyEnum.value, transactionPolicyEnum);
        }
    }

    public static DemographicConditionEnum from(String conditionName) {
        DemographicConditionEnum e = mp.get(conditionName);
        if (e == null) {
            throw new IllegalStateException();
        }
        return e;
    }
}