package com.tuannh.offer.management.commons.queue;

import java.util.concurrent.TimeUnit;
/*
Fixed-size circular queue
 */
public class TimeWindowCircularQueue {
    private static final TimeUnit BASE_UNIT = TimeUnit.SECONDS;

    private final int size;
    private final int window;
    private final TimeUnit timeUnit;

    // dt
    private long lastTs;
    private final int[] internalQueue;
    private int pointer;
    private long total;

    public TimeWindowCircularQueue(int window, TimeUnit timeUnit) {
        if (timeUnit != TimeUnit.HOURS && timeUnit != TimeUnit.MINUTES && timeUnit != TimeUnit.SECONDS) { // forced timeunit here
            throw new IllegalArgumentException();
        }
        this.window = window;
        this.timeUnit = timeUnit;
        this.size = (int)timeUnit.toSeconds(window);
        internalQueue = new int[this.size];
        lastTs = System.currentTimeMillis();
        pointer = 0;
        total = 0;
    }

    public int size() {
        return size;
    }

    private void next(long ts) {
        lastTs = ts;
        pointer++;
        pointer %= size;
        total -= internalQueue[pointer];
        internalQueue[pointer] = 0;
    }

    public void put(long timestamp) {
        long currentTs = System.currentTimeMillis();
        long nDiff = BASE_UNIT.convert(currentTs - lastTs, TimeUnit.MILLISECONDS);
        if (nDiff != 0) {
            next(currentTs);
        }
        long diff = lastTs - timestamp;
        long diffInSec = BASE_UNIT.convert(diff, TimeUnit.MILLISECONDS);
        if (diffInSec < size && diffInSec >= 0) {
            internalQueue[(int) (pointer - diffInSec)]++;
            total++;
        }
    }

    public long count() {
        return total;
    }
}
