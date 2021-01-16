package com.tuannh.offer.management.domain.policy;

import com.tuannh.offer.management.commons.visitor.Visitor;

public interface PolicyConditionVisitor<T> extends Visitor<T, Boolean> {
    Boolean visit(T data);
}