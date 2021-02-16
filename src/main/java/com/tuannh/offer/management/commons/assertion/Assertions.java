package com.tuannh.offer.management.commons.assertion;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Assertions {
    public static void inRangeCheck(int lBound, int hBound, int val) {
        if (val < lBound || val >= hBound) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
