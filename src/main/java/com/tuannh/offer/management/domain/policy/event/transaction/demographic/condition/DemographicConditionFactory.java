package com.tuannh.offer.management.domain.policy.event.transaction.demographic.condition;

import com.tuannh.offer.management.commons.assertion.Assertions;
import com.tuannh.offer.management.commons.condition.Condition;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@SuppressWarnings({"java:S3740", "rawtypes", "unchecked"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemographicConditionFactory {
    @SuppressWarnings({"unchecked", "java:S1301"})
    public static TransactionEventPolicyCondition<TransactionEvent> of(String conditionName, int argc, Object[] args) {
        DemographicConditionEnum conditionEnum = DemographicConditionEnum.from(conditionName);
        switch (conditionEnum) {
            case CUSTOMER_PROPERTIES:
                Assertions.equalChecks(argc, 1);
                return new CustomerPropertiesCondition((Map<String, Condition>)args[0]);
            default:
                throw new IllegalArgumentException();
        }
    }
}
