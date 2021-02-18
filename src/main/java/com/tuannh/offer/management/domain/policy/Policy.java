package com.tuannh.offer.management.domain.policy;

@SuppressWarnings("java:S1610")
public interface Policy<T, R> {
    R handle(T data);
}