package com.tuannh.offer.management.domain.entity.giftprogram;

import com.tuannh.offer.management.commons.fsm.FsmState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProgramFsmState {
    public static final String STRING_CREATED = "program_created";
    public static final String STRING_APPROVED = "program_approved";
    public static final String STRING_REFUSED = "program_refused";

    public static final FsmState CREATED = () -> STRING_CREATED;
    public static final FsmState APPROVED = () -> STRING_APPROVED;
    public static final FsmState REFUSED = () -> STRING_REFUSED;
}
