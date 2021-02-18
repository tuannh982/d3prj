package com.tuannh.offer.management.commons.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArrayUtils {
    public static Class[] classArray(int count, Class clazz) {
        Class[] ret = new Class[count];
        for (int i = 0; i < count; i++) {
            ret[i] = clazz;
        }
        return ret;
    }
}
