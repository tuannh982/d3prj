package com.tuannh.offer.management.domain.policy.event.transaction.frequencycap;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;

public class FrequencyCapPolicy extends TransactionEventPolicy<TransactionEvent, Boolean> {
    @Override
    public Boolean handle(TransactionEvent data) {
        return null;
    }
}
