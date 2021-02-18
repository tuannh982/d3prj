package com.tuannh.offer.management.domain.repository.giftprogram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;

import java.lang.reflect.InvocationTargetException;

public interface TransactionPolicyRepository {
    TransactionEventPolicy getPolicy(String id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, JsonProcessingException;
}
