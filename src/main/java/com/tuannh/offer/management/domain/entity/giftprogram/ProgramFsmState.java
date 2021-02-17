package com.tuannh.offer.management.domain.entity.giftprogram;

import com.tuannh.offer.management.commons.fsm.FsmState;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProgramFsmState implements FsmState {
    CREATED("program_created"),
    APPROVED("program_approved"),
    REFUSED("program_refused");
    private final String value;

    @Override
    public String value() {
        return value;
    }
}
