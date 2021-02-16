package com.tuannh.offer.management.commons.fsm;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class FsmTransitionEntryWithHandler extends FsmTransitionEntry {
    private final Handler<FsmEntity> handleBefore;
    private final Handler<FsmEntity> handleAfter;

    public FsmTransitionEntryWithHandler(
            FsmState before,
            FsmEvent event,
            FsmState after,
            @NonNull Handler<FsmEntity> handleBefore,
            @NonNull Handler<FsmEntity> handleAfter
    ) {
        super(before, event, after);
        this.handleBefore = handleBefore;
        this.handleAfter = handleAfter;
    }

    public interface Handler<E> {
        void handle(E entity);
    }

    public final void handleBefore(FsmEntity entity) {
        handleBefore.handle(entity); // no need to check null
    }

    public final void handleAfter(FsmEntity entity) {
        handleAfter.handle(entity); // no need to check null
    }
}
