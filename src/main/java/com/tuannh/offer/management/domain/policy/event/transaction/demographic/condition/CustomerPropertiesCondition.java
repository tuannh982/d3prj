package com.tuannh.offer.management.domain.policy.event.transaction.demographic.condition;

import com.tuannh.offer.management.commons.condition.Condition;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicyCondition;
import lombok.NonNull;

import java.util.Map;

@SuppressWarnings({"java:S3740", "rawtypes", "unchecked"})
public class CustomerPropertiesCondition extends TransactionEventPolicyCondition {
    private final Map<String, Condition> props;

    public CustomerPropertiesCondition(Map<String, Condition> props) {
        super(1, new Object[] {props}, new Class[] {Map.class});
        this.props = props;
    }

    public CustomerPropertiesCondition(int argc, @NonNull Object[] args, @NonNull Class[] argsType) {
        super(argc, args, argsType);
        this.props = (Map<String, Condition>) args[0];
    }

    @Override
    public boolean handle(TransactionEvent data) {
        for (Map.Entry<String, Condition> entry : props.entrySet()) {
            Map<String, Object> customerProps = data.getUser().getDemographicData();
            Object value = customerProps.get(entry.getKey());
            boolean b = entry.getValue().evaluate(value);
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
