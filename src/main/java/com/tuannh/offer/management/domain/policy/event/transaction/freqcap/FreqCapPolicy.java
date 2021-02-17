package com.tuannh.offer.management.domain.policy.event.transaction.freqcap;

import com.tuannh.offer.management.commons.assertion.Assertions;
import com.tuannh.offer.management.commons.queue.TimeWindowCircularQueue;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import lombok.NonNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FreqCapPolicy extends TransactionEventPolicy<TransactionEvent, Boolean> {
    private final int windowInSecond;
    private final int limit;
    private final TimeWindowCircularQueue cq;

    public FreqCapPolicy(int argc, @NonNull Object[] argv) {
        super(argc, argv);
        Assertions.equalChecks(argc, 2);
        limit = (int) argv[0];
        windowInSecond = (int) argv[1];
        cq = new TimeWindowCircularQueue(windowInSecond, TimeUnit.SECONDS);
    }


    @Override
    public Boolean handle(TransactionEvent data) {
        Date ts = null;
        try {
            ts = (Date) data.getProperties().get("timestamp");
            cq.put(ts.getTime());
            return cq.count() <= limit;
        } catch (Exception ignored) {/* ignored */}
        return false;
    }
}
