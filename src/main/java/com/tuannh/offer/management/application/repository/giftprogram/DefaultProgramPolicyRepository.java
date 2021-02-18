package com.tuannh.offer.management.application.repository.giftprogram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramPolicy;
import com.tuannh.offer.management.domain.exception.giftprogram.ProgramPolicyException;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionPolicyFactory;
import com.tuannh.offer.management.domain.repository.giftprogram.ProgramPolicyRepository;
import com.tuannh.offer.management.infrastructure.database.entity.TransactionPolicyDbEntity;
import com.tuannh.offer.management.infrastructure.database.repository.TransactionPolicyDbEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Slf4j
@Repository
public class DefaultProgramPolicyRepository implements ProgramPolicyRepository {
    private final TransactionPolicyDbEntityRepository transactionPolicyDbEntityRepository;

    public DefaultProgramPolicyRepository(TransactionPolicyDbEntityRepository transactionPolicyDbEntityRepository) {
        this.transactionPolicyDbEntityRepository = transactionPolicyDbEntityRepository;
    }

    @Override
    public ProgramPolicy getPolicy(String id) throws ProgramPolicyException {
        Optional<TransactionPolicyDbEntity> optionalEntity = transactionPolicyDbEntityRepository.findById(id);
        ProgramPolicy ret = null;
        if (optionalEntity.isPresent()) {
            TransactionPolicyDbEntity entity = optionalEntity.get();
            TransactionEventPolicy policy = null;
            try {
                policy = TransactionPolicyFactory.ofJsonString(
                        entity.getPolicyName(),
                        entity.getNumberOfArguments(),
                        entity.getArguments()
                );
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | JsonProcessingException e) {
                throw new ProgramPolicyException.CouldNotGetPolicy();
            }
            ret = new ProgramPolicy(id, policy);
        }
        if (ret == null) {
            throw new ProgramPolicyException.PolicyNotFound();
        }
        return ret;
    }
}
