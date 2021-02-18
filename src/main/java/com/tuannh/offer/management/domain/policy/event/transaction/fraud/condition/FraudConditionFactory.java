package com.tuannh.offer.management.domain.policy.event.transaction.fraud.condition;

import com.tuannh.offer.management.commons.assertion.Assertions;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FraudConditionFactory {
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
