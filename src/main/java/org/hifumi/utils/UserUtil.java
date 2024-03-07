package org.hifumi.utils;

public class UserUtil {

    public static Long getId() {
        return PayloadMapUtil.get("id");
    }
}
