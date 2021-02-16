package com.tuannh.offer.management.commons.fsm;

import com.tuannh.offer.management.commons.tuple.Tuple2;
import com.tuannh.offer.management.commons.tuple.Tuple3;

import java.util.HashMap;
import java.util.Map;

public class FSM {
    private final Map<Tuple2<FsmState, FsmEvent>, FsmState> transitionTable;
    private final Map<Tuple3<FsmState, FsmEvent, FsmState>, FsmTransitionEntry> executors;

    public FSM(FsmTransitionEntry... entries) {
        transitionTable = new HashMap<>();
        executors = new HashMap<>();
        for (FsmTransitionEntry entry : entries) {
            transitionTable.put(Tuple2.of(entry.getBefore(), entry.getEvent()), entry.getAfter());
            executors.put(Tuple3.of(entry.getBefore(), entry.getEvent(), entry.getAfter()), entry);
        }
    }

    public void transition(FsmEntity entity, FsmEvent event) {
        FsmState newState = transitionTable.get(Tuple2.of(entity.state(), event));
        if (newState == null) {
            throw new IllegalStateException();
        }
        FsmTransitionEntry entry = executors.get(Tuple3.of(entity.state(), event, newState));
        entity.changeState(newState);
        entry.handle(entity);
    }
}
