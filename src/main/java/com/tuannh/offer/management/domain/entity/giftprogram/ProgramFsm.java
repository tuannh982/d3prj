package com.tuannh.offer.management.domain.entity.giftprogram;

import com.tuannh.offer.management.commons.fsm.DefaultFsm;
import com.tuannh.offer.management.commons.fsm.FsmTransitionEntry;

public class ProgramFsm extends DefaultFsm {
    private static final FsmTransitionEntry[] transitionTable = new FsmTransitionEntry[] {
            new FsmTransitionEntry(
                    ProgramFsmState.CREATED,
                    ProgramStateTransitionEvent.APPROVE,
                    ProgramFsmState.APPROVED
            ),
            new FsmTransitionEntry(
                    ProgramFsmState.CREATED,
                    ProgramStateTransitionEvent.REFUSE,
                    ProgramFsmState.REFUSED
            )
    };

    public ProgramFsm() {
        super(transitionTable);
    }
}
