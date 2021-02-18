package com.tuannh.offer.management.domain.entity.giftprogram;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProgramPolicy {
    private final String id;
    private final TransactionEventPolicy policy;

    public boolean handle(TransactionEvent data) {
        return policy.handle(data);
    }
}
