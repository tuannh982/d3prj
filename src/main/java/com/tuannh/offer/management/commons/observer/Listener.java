package com.tuannh.offer.management.commons.observer;

public interface Listener<T> {
    void onEvent(T event);
}
