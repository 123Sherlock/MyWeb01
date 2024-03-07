package org.hifumi.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 身份验证令牌
 * 具体查看JWT类的注释
 *
 * @see JWT
 */
public class JWTUtil {

    private static final byte[] KEY = "asahi".getBytes(StandardCharsets.UTF_8);

    public static String genToken(Map<String, ?> payloads) {
        return JWT.create()
            .setKey(KEY)
            .addPayloads(payloads)
            .setExpiresAt(DateUtil.date().offset(DateField.SECOND, 1))
            .sign();
    }

    public static Map<String, ?> parseToken(String token) {
        return JWT.of(token)
            .setKey(KEY)
            .getPayloads()
            .getRaw();
    }
}
