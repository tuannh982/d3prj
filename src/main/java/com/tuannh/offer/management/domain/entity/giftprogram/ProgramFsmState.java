package com.tuannh.offer.management.domain.entity.giftprogram;

import com.tuannh.offer.management.commons.fsm.FsmState;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<String, ProgramFsmState> mp;
    static {
        mp = new HashMap<>();
        for (ProgramFsmState programFsmState : ProgramFsmState.values()) {
            mp.put(programFsmState.value, programFsmState);
        }
    }

    public static ProgramFsmState from(String val) {
        ProgramFsmState ret = mp.get(val);
        if (ret == null) throw new IllegalStateException();
        return ret;
    }
}
