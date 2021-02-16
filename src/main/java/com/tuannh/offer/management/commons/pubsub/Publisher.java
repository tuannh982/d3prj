package com.tuannh.offer.management.commons.pubsub;

public interface Publisher<T> {
    String topic();
    void publish(T data);
}
