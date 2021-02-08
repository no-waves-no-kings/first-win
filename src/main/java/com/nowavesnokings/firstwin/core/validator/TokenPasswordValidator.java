package com.nowavesnokings.firstwin.core.validator;

import com.nowavesnokings.firstwin.core.annotation.TokenPassword;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ssx
 * @version V1.0
 * @className TokenPasswordValidator
 * @description 令牌密码验证
 * @date 2020-12-14 15:53
 * @since 1.8
 */
public class TokenPasswordValidator implements ConstraintValidator<TokenPassword, String> {
    private int min;

    private int max;

    @Override
    public void initialize(TokenPassword constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(s)) {
            return true;
        }
        return s.length() >= min && s.length() <= max;
    }
}
