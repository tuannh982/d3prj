package com.tuannh.offer.management.domain.policy;

import com.tuannh.offer.management.commons.visitor.Acceptor;

public interface PolicyConditionAcceptor<T> extends Acceptor<T, Boolean> {
}
