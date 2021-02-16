package com.tuannh.offer.management.commons.fsm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FsmTransitionEntry {
    private final FsmState before;
    private final FsmEvent event;
    private final FsmState after;
}
