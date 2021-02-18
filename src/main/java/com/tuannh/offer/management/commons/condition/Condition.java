package com.tuannh.offer.management.commons.condition;

import com.tuannh.offer.management.commons.args.MarkerClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Condition<T> extends MarkerClass {
    @NonNull
    private Operator operator;
    private T expectedValue;

    @SuppressWarnings("unchecked")
    public boolean evaluate(T value) {
        if (expectedValue == null && value == null) {
            return true;
        } else if (expectedValue == null) {
            return false;
        } else if (value == null) {
            return false;
        }
        if (value == expectedValue) {
            return true;
        }
        try {
            int compareResult = ((Comparable<T>) value).compareTo(expectedValue); // both are not null
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
        } catch (Exception ignored) {
            // could not cast to Comparator, hence they're not equal
            return false;
        }

    }
}
