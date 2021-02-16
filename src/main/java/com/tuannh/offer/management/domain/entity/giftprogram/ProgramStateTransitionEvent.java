package com.tuannh.offer.management.domain.entity.giftprogram;

import com.tuannh.offer.management.commons.fsm.FsmEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProgramStateTransitionEvent {
    public static final String STRING_APPROVE = "approve_program";
    public static final String STRING_REFUSE = "refuse_program";

    public static final FsmEvent APPROVE = () -> STRING_APPROVE;
    public static final FsmEvent REFUSE = () -> STRING_REFUSE;
}
