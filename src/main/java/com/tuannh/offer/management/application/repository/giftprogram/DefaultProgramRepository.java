package com.tuannh.offer.management.application.repository.giftprogram;

import com.tuannh.offer.management.domain.entity.giftprogram.Program;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramFsmState;
import com.tuannh.offer.management.domain.entity.reward.Reward;
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

    public DefaultProgramRepository(ProgramDbEntityRepository programDbEntityRepository, RewardDbEntityRepository rewardDbEntityRepository) {
        this.programDbEntityRepository = programDbEntityRepository;
        this.rewardDbEntityRepository = rewardDbEntityRepository;
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
                null, // FIXME
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
    public Program get(String programName) {
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
        return new Program(
            entity.getProgramName(),
                entity.getPartnerCode(),
                entity.getProgramEffectiveFrom(),
                entity.getProgramEffectiveTo(),
                null, // FIXME
                entity.getMaxRewardPerUser(),
                rewardList,
                ProgramFsmState.from(entity.getStatus())
        );
    }

    // TODO FIXME
    @Override
    public List<Program> getProgramsWithinTimeRange(Date from, Date to) {
        return null;
    }
}
