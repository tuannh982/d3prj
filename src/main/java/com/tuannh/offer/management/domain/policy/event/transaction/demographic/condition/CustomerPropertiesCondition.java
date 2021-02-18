package com.tuannh.offer.management.domain.policy.event.transaction.demographic.condition;

import com.tuannh.offer.management.commons.condition.Condition;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;

import java.util.Map;

@SuppressWarnings({"java:S3740", "rawtypes", "unchecked"})
public class CustomerPropertiesCondition implements TransactionEventPolicyCondition<TransactionEvent> {
    private final Map<String, Condition> props;

    public CustomerPropertiesCondition(Map<String, Condition> props) {
        this.props = props;
    }

    @Override
    public boolean handle(TransactionEvent data) {
        for (Map.Entry<String, Condition> entry : props.entrySet()) {
            Map<String, Object> customerProps = data.getUser().getDemographicData();
            Object value = customerProps.get(entry.getKey());
            boolean b = entry.getValue().evaluate((Comparable) value);
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
