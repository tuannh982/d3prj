package com.tuannh.offer.management.domain.service.interfaces;

import com.tuannh.offer.management.domain.entity.giftprogram.Program;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramFsmState;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramPolicy;
import com.tuannh.offer.management.domain.entity.reward.Reward;

import java.util.Date;
import java.util.List;

public interface ProgramService {
    String createProgram(
            String programName,
            String partnerCode,
            Date programEffectiveFrom,
            Date programEffectiveTo,
            ProgramPolicy policy,
            Integer maxRewardPerUser,
            List<Reward> rewardList
    );

    List<Program> getPrograms(Date programEffectiveFrom, Date programEffectiveTo);
    List<Program> getPrograms(Date programEffectiveFrom, Date programEffectiveTo, ProgramFsmState state);

    default List<Program> getNeedForApprovalPrograms(Date programEffectiveFrom, Date programEffectiveTo) {
        return getPrograms(programEffectiveFrom, programEffectiveTo, ProgramFsmState.CREATED);
    }

    default List<Program> getApprovedPrograms(Date programEffectiveFrom, Date programEffectiveTo) {
        return getPrograms(programEffectiveFrom, programEffectiveTo, ProgramFsmState.APPROVED);
    }
}
