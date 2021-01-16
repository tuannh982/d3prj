package com.tuannh.offer.management.commons.visitor;

public interface Visitor<T, R> {
    R visit(T o);
}