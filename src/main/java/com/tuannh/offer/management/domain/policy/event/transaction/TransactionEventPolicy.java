package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.EventPolicy;

public abstract class TransactionEventPolicy<T extends TransactionEvent, R extends Boolean> extends EventPolicy<T, R> {
}
