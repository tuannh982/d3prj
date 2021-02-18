package com.tuannh.offer.management.commons.args;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuannh.offer.management.commons.condition.Condition;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class MarkerClass {
    @JsonIgnore
    protected String marker;

    protected static final Map<String, Class> markerTable = new HashMap<>();

    // register classes here
    static {
        markerTable.put("condition", Condition.class);
    }
}
