package com.tuannh.offer.management.domain.entity.giftprogram;

import com.tuannh.offer.management.commons.fsm.FsmEvent;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum ProgramStateTransitionEvent implements FsmEvent {
    APPROVE("approve_program"),
    REFUSE("refuse_program");

    private final String value;

    @Override
    public String value() {
        return value;
    }

    private static final Map<String, ProgramStateTransitionEvent> mp;
    static {
        mp = new HashMap<>();
        for (ProgramStateTransitionEvent programStateTransitionEvent : ProgramStateTransitionEvent.values()) {
            mp.put(programStateTransitionEvent.value, programStateTransitionEvent);
        }
    }

    public static ProgramStateTransitionEvent from(String val) {
        ProgramStateTransitionEvent ret = mp.get(val);
        if (ret == null) throw new IllegalStateException();
        return ret;
    }
}
