package com.tuannh.offer.management.commons.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Operator {
    GT(">"),
    LT("<"),
    EQ("="),
    GE(">="),
    LE("<=");

    private final String value;

    private static final Map<String, Operator> map;
    static {
        Map<String, Operator> mx = new HashMap<>();
        for (Operator op : Operator.values()) {
            mx.put(op.getValue(), op);
        }
        map = Collections.unmodifiableMap(mx);
    }

    public static Operator fromString(String s) {
        return map.get(s);
    }
}
