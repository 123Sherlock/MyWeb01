package org.hifumi.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum UserStatus {
    NORMAL(0, "正常"),
    FREEZE(1, "冻结"),
    ;

    /**
     * EnumValue注解用来指定与数据库映射的字段
     */
    @EnumValue
    int value;

    /**
     * JsonValue注解用来指定返回给前端时JSON读取的字段
     */
    @JsonValue
    String desc;
}
