package com.tuannh.offer.management.domain.policy.event.transaction.fraud;

import com.tuannh.offer.management.commons.assertion.Assertions;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FraudConditionFactory {
    @AllArgsConstructor
    public enum FraudConditionEnum {
        EVENT_BLACK_LIST("event_condition", EventBlackListCondition.class),
        USER_BLACK_LIST("user_condition", UserBlackListCondition.class);
        private final String value;
        private final Class<? extends TransactionEventPolicyCondition<TransactionEvent>> clazz;

        private static Map<String, FraudConditionEnum> mp;
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

    @SuppressWarnings("unchecked")
    public static TransactionEventPolicyCondition<TransactionEvent> of(String conditionName, int argc, Object[] args) {
        FraudConditionEnum conditionEnum = FraudConditionEnum.from(conditionName);
        switch (conditionEnum) {
            case USER_BLACK_LIST:
                Assertions.equalChecks(argc, 1);
                return new UserBlackListCondition((List<String>)args[0]);
            case EVENT_BLACK_LIST:
                Assertions.equalChecks(argc, 1);
                return new EventBlackListCondition((List<String>)args[0]);
            default:
                throw new IllegalArgumentException();
        }
    }
}
