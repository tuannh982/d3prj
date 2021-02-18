package com.tuannh.offer.management.application.repository.giftprogram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionPolicyFactory;
import com.tuannh.offer.management.domain.repository.giftprogram.TransactionPolicyRepository;
import com.tuannh.offer.management.infrastructure.database.entity.TransactionPolicyDbEntity;
import com.tuannh.offer.management.infrastructure.database.repository.TransactionPolicyDbEntityRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Repository
public class DefaultTransactionPolicyRepository implements TransactionPolicyRepository {
    private final TransactionPolicyDbEntityRepository transactionPolicyDbEntityRepository;

    public DefaultTransactionPolicyRepository(TransactionPolicyDbEntityRepository transactionPolicyDbEntityRepository) {
        this.transactionPolicyDbEntityRepository = transactionPolicyDbEntityRepository;
    }

    @Override
    public TransactionEventPolicy getPolicy(String id)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, JsonProcessingException {
        Optional<TransactionPolicyDbEntity> optionalEntity = transactionPolicyDbEntityRepository.findById(id);
        if (optionalEntity.isPresent()) {
            TransactionPolicyDbEntity entity = optionalEntity.get();
            return TransactionPolicyFactory.ofJsonString(
                    entity.getPolicyName(),
                    entity.getNumberOfArguments(),
                    entity.getArguments()
            );
        }
        return null;
    }
}
