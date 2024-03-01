package org.hifumi.exception;

import cn.hutool.core.util.StrUtil;
import org.hifumi.domain.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestControllerAdvice注解组合了@ResponseBody和@ControllerAdvice
 * ResponseBody注解用来把本类所有方法的返回值转换为JSON
 * ControllerAdvice可用来配合@ExceptionHandler使用
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ExceptionHandler注解可以用来统一处理所有捕获到的指定异常
     * 还需在类上添加@ControllerAdvice
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        e.printStackTrace();
        return Result.fail(StrUtil.isNotEmpty(e.getMessage()) ? e.getMessage() : "操作失败");
    }
}
