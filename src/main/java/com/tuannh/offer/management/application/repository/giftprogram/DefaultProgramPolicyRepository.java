package com.tuannh.offer.management.application.repository.giftprogram;

import com.tuannh.offer.management.commons.args.JsonArgs;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramPolicy;
import com.tuannh.offer.management.domain.exception.giftprogram.ProgramPolicyException;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import com.tuannh.offer.management.domain.repository.giftprogram.ProgramPolicyRepository;
import com.tuannh.offer.management.infrastructure.database.entity.TransactionPolicyDbEntity;
import com.tuannh.offer.management.infrastructure.database.repository.TransactionPolicyDbEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static com.tuannh.offer.management.commons.args.JsonArgs.from;

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
                policy = (TransactionEventPolicy) JsonArgs.from(
                        entity.getSpec()
                );
            } catch (
                    NoSuchMethodException |
                    IllegalAccessException |
                    InvocationTargetException |
                    InstantiationException |
                    IOException e
            ) {
                throw new ProgramPolicyException.CouldNotGetPolicy(e);
            }
            ret = new ProgramPolicy(id, policy);
        }
        if (ret == null) {
            throw new ProgramPolicyException.PolicyNotFound();
        }
        return ret;
    }
}
