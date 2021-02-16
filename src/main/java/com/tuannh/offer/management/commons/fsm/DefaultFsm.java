package com.tuannh.offer.management.commons.fsm;

import com.tuannh.offer.management.commons.tuple.Tuple2;

public class DefaultFsm extends FSM {
    public DefaultFsm(FsmTransitionEntry... entries) {
        super(entries);
    }

    public void transition(FsmEntity entity, FsmEvent event) {
        FsmState newState = transitionTable.get(Tuple2.of(entity.state(), event));
        if (newState == null) {
            throw new IllegalStateException();
        }
        entity.changeState(newState);
    }
}
