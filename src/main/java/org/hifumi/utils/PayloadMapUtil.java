package org.hifumi.utils;

import java.util.Map;

public class PayloadMapUtil {

    private static final ThreadLocal<Map<String, ?>> PAYLOAD_MAP = new ThreadLocal<>();

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) PAYLOAD_MAP.get().get(key);
    }

    public static void set(Map<String, ?> payloadMap) {
        PAYLOAD_MAP.set(payloadMap);
    }

    public static void remove() {
        PAYLOAD_MAP.remove();
    }
}
