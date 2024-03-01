package org.hifumi.domain.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * 统一返回给前端的操作结果
 * @param <T> 如果返回结果包含额外数据，则是对应的VO类
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Result<T> {

    Integer code;

    String message;

    T data;

    static Integer SUC_CODE = 0;
    static String SUC_MSG = "操作成功";

    static Integer FAIL_CODE = 1;
    static String FAIL_MSG = "操作失败";

    public static <D> Result<D> success(D data) {
        return new Result<>(SUC_CODE, SUC_MSG, data);
    }

    public static Result<?> success() {
        return new Result<>(SUC_CODE, SUC_MSG, null);
    }

    public static Result<?> fail() {
        return new Result<>(FAIL_CODE, FAIL_MSG, null);
    }

    public static Result<?> fail(String message) {
        return new Result<>(FAIL_CODE, message, null);
    }
}
