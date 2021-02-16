package com.tuannh.offer.management.commons.fsm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FsmTransitionEntry {
    private final FsmState before;
    private final FsmEvent event;
    private final FsmState after;
    private final Handler<FsmEntity> handler;

    public interface Handler<E> {
        void handle(E entity);
    }

    public final void handle(FsmEntity entity) {
        if (handler != null) {
            handler.handle(entity);
        }
    }
}
