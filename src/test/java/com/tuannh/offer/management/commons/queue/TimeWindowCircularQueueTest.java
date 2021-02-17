package com.tuannh.offer.management.commons.queue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeWindowCircularQueueTest {
    @Test
    void circularQueueTest() throws InterruptedException {
        TimeWindowCircularQueue queue = new TimeWindowCircularQueue(2, TimeUnit.SECONDS);
        long baseTs = System.currentTimeMillis();
        long baseTsAndHalf = baseTs + 500;
        long baseTsPlusOne = baseTs + 1000;
        long baseTsPlusOneAndHalf = baseTs + 1500;
        long baseTsPlusTwoAndHalf = baseTs + 2500;
        assertEquals(0, queue.count()); // 0
        Thread.sleep(500);
        for (int i = 0; i < 2; i++) {
            queue.put(baseTsAndHalf);
        }
        assertEquals(2, queue.count()); // 0(ts=0) + 2(ts=500)
        Thread.sleep(500);
        for (int i = 0; i < 3; i++) {
            queue.put(baseTsPlusOne);
        }
        assertEquals(5, queue.count()); // 0(ts=0) + 2(ts=500) + 3(ts=1000)
        Thread.sleep(500);
        for (int i = 0; i < 4; i++) {
            queue.put(baseTsPlusOneAndHalf);
        }
        assertEquals(9, queue.count()); // 0(ts=0) + 2(ts=500) + 3(ts=1000) + 4(ts=1500)
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            queue.put(baseTsPlusTwoAndHalf);
        }
        assertEquals(12, queue.count()); // 0(ts=0) + 2(ts=500) + 3(ts=1000) + 4(ts=1500) - 2(ts=500) + 5(ts=2500)
    }
}
