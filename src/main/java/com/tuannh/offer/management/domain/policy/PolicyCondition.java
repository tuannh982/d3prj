package com.tuannh.offer.management.domain.policy;

public interface PolicyCondition<T> {
    boolean handle(T data);
}
