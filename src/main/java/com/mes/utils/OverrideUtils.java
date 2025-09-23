package com.mes.utils;
import java.util.HashMap;
import java.util.Map;

public class OverrideUtils {

    public static Map<String, Object> toMap(Object... keyValues) {
        if (keyValues.length % 2 != 0) {
            throw new IllegalArgumentException("Arguments must be in key-value pairs");
        }

        Map<String, Object> overrides = new HashMap<>();
        for (int i = 0; i < keyValues.length; i += 2) {
            String key = keyValues[i].toString();
            Object value = keyValues[i + 1];
            overrides.put(key, value);
        }
        return overrides;
    }
}
