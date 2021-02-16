package com.tuannh.offer.management.commons.queue;

import java.util.concurrent.TimeUnit;
/*
Fixed-size circular queue
 */
public class TimeWindowCircularQueue<T> {
    private final int size;
    private final long window;
    private final TimeUnit timeUnit;

    public TimeWindowCircularQueue(int size, long window, TimeUnit timeUnit) {
        this.size = size;
        this.window = window;
        this.timeUnit = timeUnit;
    }

    public int size() {
        return size;
    }
}
