package com.tuannh.offer.management.commons.condition;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Condition<T extends Comparable<T>> {
    @NonNull
    private final Operator operator;
    private final T expectedValue;

    public boolean evaluate(T value) {
        if (expectedValue == null && value == null) {
            return true;
        } else if (expectedValue == null) {
            return false;
        } else if (value == null) {
            return false;
        }
        int compareResult = value.compareTo(expectedValue); // both are not null
        switch (operator) {
            case EQ:
                return compareResult == 0;
            case GT:
                return compareResult > 0;
            case LT:
                return compareResult < 0;
            case GE:
                return compareResult >= 0;
            case LE:
                return compareResult <= 0;
            default:
                return false;
        }
    }
}
