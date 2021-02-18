package com.tuannh.offer.management.domain.repository.giftprogram;

import com.tuannh.offer.management.domain.entity.giftprogram.ProgramPolicy;
import com.tuannh.offer.management.domain.exception.giftprogram.ProgramPolicyException;

public interface ProgramPolicyRepository {
    ProgramPolicy getPolicy(String id) throws ProgramPolicyException;
}
