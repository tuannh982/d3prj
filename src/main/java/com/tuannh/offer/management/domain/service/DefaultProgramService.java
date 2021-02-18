package com.tuannh.offer.management.domain.service;

import com.tuannh.offer.management.domain.entity.giftprogram.Program;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramFsmState;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramPolicy;
import com.tuannh.offer.management.domain.entity.reward.Reward;
import com.tuannh.offer.management.domain.policy.event.transaction.TransactionEventPolicy;
import com.tuannh.offer.management.domain.repository.giftprogram.ProgramRepository;
import com.tuannh.offer.management.domain.service.interfaces.ProgramService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultProgramService implements ProgramService {
    private final ProgramRepository programRepository;

    public DefaultProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public String createProgram(
            String programName,
            String partnerCode,
            Date programEffectiveFrom,
            Date programEffectiveTo,
            ProgramPolicy policy,
            Integer maxRewardPerUser,
            List<Reward> rewardList
    ) {
        programRepository.save(new Program(
                        programName,
                        partnerCode,
                        programEffectiveFrom,
                        programEffectiveTo,
                        policy,
                        maxRewardPerUser,
                        rewardList,
                        ProgramFsmState.CREATED
                )
        );
        return programName;
    }

    @Override
    public List<Program> getPrograms(Date programEffectiveFrom, Date programEffectiveTo) {
        return programRepository.getProgramsWithinTimeRange(programEffectiveFrom, programEffectiveTo);
    }

    @Override
    public List<Program> getPrograms(Date programEffectiveFrom, Date programEffectiveTo, ProgramFsmState state) {
        List<Program> progs = getPrograms(programEffectiveFrom, programEffectiveTo);
        if (state == null) {
            return progs;
        }
        List<Program> ret = new ArrayList<>();
        for (Program prog : progs) {
            if (prog.state() == state) {
                ret.add(prog);
            }
        }
        return ret;
    }
}
