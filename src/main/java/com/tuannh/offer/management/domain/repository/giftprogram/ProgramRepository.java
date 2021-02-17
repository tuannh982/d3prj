package com.tuannh.offer.management.domain.repository.giftprogram;

import com.tuannh.offer.management.domain.entity.giftprogram.Program;
import com.tuannh.offer.management.domain.entity.giftprogram.ProgramFsmState;

import java.util.Date;
import java.util.List;

public interface ProgramRepository {
    void save(Program program);
    void updateApprovalStatus(String programName, ProgramFsmState newState);
    Program get(String programName);
    List<Program> getProgramsWithinTimeRange(Date from, Date to);
}
