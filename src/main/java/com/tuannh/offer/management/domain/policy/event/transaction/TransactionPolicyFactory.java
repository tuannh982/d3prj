package com.tuannh.offer.management.domain.policy.event.transaction;

import com.tuannh.offer.management.domain.event.TransactionEvent;
import com.tuannh.offer.management.domain.policy.event.transaction.chain.ChainPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.fraud.FraudPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.freqcap.FreqCapPolicy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionPolicyFactory {
    @AllArgsConstructor
    public enum TransactionPolicyEnum {
        FRAUD("fraud", FraudPolicy.class),
        FREQ_CAP("freq_cap", FreqCapPolicy.class),
        CHAIN("chain", ChainPolicy.class);
        private final String value;
        private final Class<? extends TransactionEventPolicy<TransactionEvent, Boolean>> clazz;

        private static Map<String, TransactionPolicyEnum> mp;
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
}
