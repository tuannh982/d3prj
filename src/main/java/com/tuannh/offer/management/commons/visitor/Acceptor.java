package com.tuannh.offer.management.commons.visitor;

public interface Acceptor<T, R> {
    R accept(Visitor<T, R> visitor);
}