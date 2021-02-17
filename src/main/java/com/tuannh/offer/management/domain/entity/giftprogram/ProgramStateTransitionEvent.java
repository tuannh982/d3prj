package com.tuannh.offer.management.domain.entity.giftprogram;

import com.tuannh.offer.management.commons.fsm.FsmEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProgramStateTransitionEvent implements FsmEvent {
    APPROVE("approve_program"),
    REFUSE("refuse_program");

    private final String value;

    @Override
    public String value() {
        return value;
    }
}
