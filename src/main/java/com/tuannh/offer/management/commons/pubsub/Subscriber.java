package com.tuannh.offer.management.commons.pubsub;

public interface Subscriber<T> {
    String topic();
    void handle(T data);
}
