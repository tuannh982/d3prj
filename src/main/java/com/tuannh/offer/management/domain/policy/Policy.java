package com.tuannh.offer.management.domain.policy;

@SuppressWarnings("java:S1610")
public abstract class Policy<T, R> {
    public abstract R handle(T data);
}