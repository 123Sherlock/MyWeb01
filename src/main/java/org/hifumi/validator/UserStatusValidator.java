package org.hifumi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hifumi.annotation.validate.CheckUserStatus;
import org.hifumi.enums.UserStatus;

/**
 * 自定义校验注解的具体校验类
 * 实现ConstraintValidator接口，第一个泛型为校验用的注解，第二个泛型为需要校验参数的类型
 */
public class UserStatusValidator implements ConstraintValidator<CheckUserStatus, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value == UserStatus.NORMAL.getValue() || value == UserStatus.FREEZE.getValue();
    }
}
