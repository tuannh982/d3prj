package com.tuannh.offer.management.commons.fsm;

import com.tuannh.offer.management.commons.tuple.Tuple2;
import com.tuannh.offer.management.commons.tuple.Tuple3;

public class DefaultFsmWithHandler extends FSM {
    public DefaultFsmWithHandler(FsmTransitionEntryWithHandler... entries) {
        super(entries);
    }

    public void transition(FsmEntity entity, FsmEvent event) {
        FsmState newState = transitionTable.get(Tuple2.of(entity.state(), event));
        if (newState == null) {
            throw new IllegalStateException();
        }
        FsmTransitionEntryWithHandler entry = (FsmTransitionEntryWithHandler) executors.get(Tuple3.of(entity.state(), event, newState));
        // ------------------------------------------------
        entry.handleBefore(entity);
        entity.changeState(newState);
        entry.handleAfter(entity);
    }
}
