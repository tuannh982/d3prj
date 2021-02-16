package com.tuannh.offer.management.commons.fsm;

import com.tuannh.offer.management.commons.tuple.Tuple2;
import com.tuannh.offer.management.commons.tuple.Tuple3;

import java.util.HashMap;
import java.util.Map;

public abstract class FSM {
    protected final Map<Tuple2<FsmState, FsmEvent>, FsmState> transitionTable;
    protected final Map<Tuple3<FsmState, FsmEvent, FsmState>, FsmTransitionEntry> executors;

    protected FSM(FsmTransitionEntry... entries) {
        transitionTable = new HashMap<>();
        executors = new HashMap<>();
        for (FsmTransitionEntry entry : entries) {
            transitionTable.put(Tuple2.of(entry.getBefore(), entry.getEvent()), entry.getAfter());
            executors.put(Tuple3.of(entry.getBefore(), entry.getEvent(), entry.getAfter()), entry);
        }
    }

    public abstract void transition(FsmEntity entity, FsmEvent event);
}
