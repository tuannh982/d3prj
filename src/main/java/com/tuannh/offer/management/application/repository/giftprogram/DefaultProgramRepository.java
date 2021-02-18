package com.tuannh.offer.management.application.repository.giftprogram;

import com.tuannh.offer.management.domain.entity.giftprogram.Program;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramFsmState;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramPolicy;
import com.tuannh.offer.management.domain.entity.reward.Reward;
import com.tuannh.offer.management.domain.exception.giftprogram.ProgramException;
import com.tuannh.offer.management.domain.exception.giftprogram.ProgramPolicyException;
import com.tuannh.offer.management.domain.repository.giftprogram.ProgramPolicyRepository;
import com.tuannh.offer.management.domain.repository.giftprogram.ProgramRepository;
import com.tuannh.offer.management.infrastructure.database.entity.ProgramDbEntity;
import com.tuannh.offer.management.infrastructure.database.entity.RewardDbEntity;
import com.tuannh.offer.management.infrastructure.database.repository.ProgramDbEntityRepository;
import com.tuannh.offer.management.infrastructure.database.repository.RewardDbEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DefaultProgramRepository implements ProgramRepository {
    private final ProgramDbEntityRepository programDbEntityRepository;
    private final RewardDbEntityRepository rewardDbEntityRepository;
    private final ProgramPolicyRepository programPolicyRepository;

    public DefaultProgramRepository(ProgramDbEntityRepository programDbEntityRepository, RewardDbEntityRepository rewardDbEntityRepository, ProgramPolicyRepository programPolicyRepository) {
        this.programDbEntityRepository = programDbEntityRepository;
        this.rewardDbEntityRepository = rewardDbEntityRepository;
        this.programPolicyRepository = programPolicyRepository;
    }

    @Override
    public void save(Program program) {
        List<String> rewardCodes = new ArrayList<>();
        for (Reward reward : program.getRewardList()) {
            rewardCodes.add(reward.getRewardCode());
        }
        programDbEntityRepository.save(new ProgramDbEntity(
                program.getProgramName(),
                program.getPartnerCode(),
                program.getProgramEffectiveFrom(),
                program.getProgramEffectiveTo(),
                program.getPolicy().getId(),
                program.getMaxRewardPerUser(),
                rewardCodes,
                ProgramFsmState.CREATED.value()
        ));
    }

    @Override
    public void updateApprovalStatus(String programName, ProgramFsmState newState) {
        programDbEntityRepository.updateStatus(programName, newState.value());
    }

    @Override
    public Program get(String programName) throws ProgramException {
        ProgramDbEntity entity = programDbEntityRepository.getOne(programName);
        List<RewardDbEntity> rewards = rewardDbEntityRepository.getAllByRewardCodeIn(entity.getRewardList());
        List<Reward> rewardList = new ArrayList<>();
        for (RewardDbEntity rewardDbEntity : rewards) {
            rewardList.add(new Reward(
                    rewardDbEntity.getRewardCode(),
                    rewardDbEntity.getRewardEffectiveFrom(),
                    rewardDbEntity.getRewardEffectiveTo(),
                    rewardDbEntity.isUsed()
            ));
        }
        try {
            ProgramPolicy policy = programPolicyRepository.getPolicy(entity.getPolicyId());
            return new Program(
                    entity.getProgramName(),
                    entity.getPartnerCode(),
                    entity.getProgramEffectiveFrom(),
                    entity.getProgramEffectiveTo(),
                    policy,
                    entity.getMaxRewardPerUser(),
                    rewardList,
                    ProgramFsmState.from(entity.getStatus())
            );
        } catch (ProgramPolicyException e) {
            throw new ProgramException.CouldNotGetProgram(e);
        }
    }

    // TODO FIXME
    @Override
    public List<Program> getProgramsWithinTimeRange(Date from, Date to) {
        return null;
    }
}
