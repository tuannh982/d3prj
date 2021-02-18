package com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum FraudConditionEnum {
    EVENT_BLACK_LIST("event_condition", EventBlackListCondition.class),
    USER_BLACK_LIST("user_condition", UserBlackListCondition.class);
    private final String value;
    private final Class<? extends TransactionEventPolicyCondition<TransactionEvent>> clazz;

    private static final Map<String, FraudConditionEnum> mp;
    static {
        mp = new HashMap<>();
        for (FraudConditionEnum transactionPolicyEnum : FraudConditionEnum.values()) {
            mp.put(transactionPolicyEnum.value, transactionPolicyEnum);
        }
    }

    public static FraudConditionEnum from(String conditionName) {
        FraudConditionEnum e = mp.get(conditionName);
        if (e == null) {
            throw new IllegalStateException();
        }
        return e;
    }
}