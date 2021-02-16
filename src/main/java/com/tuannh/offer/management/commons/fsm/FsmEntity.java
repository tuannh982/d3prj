package com.tuannh.offer.management.commons.fsm;

public interface FsmEntity {
    FsmState state();
    void changeState(FsmState newState);
}
