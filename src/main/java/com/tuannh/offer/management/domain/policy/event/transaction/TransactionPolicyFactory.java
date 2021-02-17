package com.tuannh.offer.management.domain.policy.event.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.chain.ChainPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.fraud.FraudPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.freqcap.FreqCapPolicy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionPolicyFactory {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @AllArgsConstructor
    public enum TransactionPolicyEnum {
        FRAUD("fraud", FraudPolicy.class),
        FREQ_CAP("freq_cap", FreqCapPolicy.class),
        CHAIN("chain", ChainPolicy.class);
        private final String value;
        private final Class<? extends TransactionEventPolicy<TransactionEvent, Boolean>> clazz;

        private static final Map<String, TransactionPolicyEnum> mp;
        static {
            mp = new HashMap<>();
            for (TransactionPolicyEnum transactionPolicyEnum : TransactionPolicyEnum.values()) {
                mp.put(transactionPolicyEnum.value, transactionPolicyEnum);
            }
        }

        public static Class<? extends TransactionEventPolicy<TransactionEvent, Boolean>> getClass(String policyName) {
            TransactionPolicyEnum e = mp.get(policyName);
            if (e == null) {
                throw new IllegalStateException();
            }
            return e.clazz;
        }

        public static TransactionPolicyEnum of(String policyName) {
            TransactionPolicyEnum e = mp.get(policyName);
            if (e == null) {
                throw new IllegalStateException();
            }
            return e;
        }
    }

    public static TransactionEventPolicy<TransactionEvent, Boolean> of(String policyName, int argc, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<? extends TransactionEventPolicy<TransactionEvent, Boolean>> clazz = TransactionPolicyEnum.getClass(policyName);
        Constructor<? extends TransactionEventPolicy<TransactionEvent, Boolean>> constructor = null;
        TransactionEventPolicy<TransactionEvent, Boolean> instance = null;
        if (argc == 0) {
            constructor = clazz.getConstructor();
            instance = constructor.newInstance();
        } else {
            constructor = clazz.getConstructor(int.class, Object[].class);
            instance = constructor.newInstance(argc, args);
        }
        return instance;
    }

    public static TransactionEventPolicy<TransactionEvent, Boolean> ofJsonString(String policyName, int argc, String args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JsonProcessingException {
        Class<? extends TransactionEventPolicy<TransactionEvent, Boolean>> clazz = TransactionPolicyEnum.getClass(policyName);
        Constructor<? extends TransactionEventPolicy<TransactionEvent, Boolean>> constructor = null;
        TransactionEventPolicy<TransactionEvent, Boolean> instance = null;
        if (argc == 0) {
            constructor = clazz.getConstructor();
            instance = constructor.newInstance();
        } else {
            constructor = clazz.getConstructor(int.class, Object[].class);
            if (ClassUtils.isAssignable(clazz, ConditionArgumentTransactionEventPolicy.class)) {
                ConditionArgumentTransactionEventPolicy.ConditionArgs[] conditionArgs = JSON_MAPPER.readValue(
                        args, ConditionArgumentTransactionEventPolicy.ConditionArgs[].class
                );
                instance = constructor.newInstance(argc, conditionArgs);
            } else if (ClassUtils.isAssignable(clazz, PolicyArgumentTransactionEventPolicy.class)) {
                PolicyArgumentTransactionEventPolicy.PolicyArgs[] policyArgs = JSON_MAPPER.readValue(
                        args, PolicyArgumentTransactionEventPolicy.PolicyArgs[].class
                );
                instance = constructor.newInstance(argc, policyArgs);
            } else {
                Object[] objectArgs = JSON_MAPPER.readValue(args, Object[].class);
                instance = constructor.newInstance(argc, objectArgs);
            }
        }
        return instance;
    }
}
