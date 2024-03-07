package org.hifumi.annotation.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import org.hifumi.validator.UserStatusValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验注解
 * 需要包含三个方法，可以从其他校验注解如@NotNull里复制
 *
 * @see NotNull
 * 需要添加@Constraint，指定具体执行校验的类
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UserStatusValidator.class})
public @interface CheckUserStatus {

    String message() default "用户状态只能是0或者1";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
