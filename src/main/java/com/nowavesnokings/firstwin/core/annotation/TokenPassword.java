package com.nowavesnokings.firstwin.core.annotation;

import com.nowavesnokings.firstwin.core.validator.TokenPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ssx
 * @version V1.0
 * @className TokenPassword
 * @description 令牌中密码验证注解
 * @date 2020-12-14 15:44
 * @since 1.8
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = TokenPasswordValidator.class)
public @interface TokenPassword {
    int min() default 8;

    int max() default 32;

    String message() default "{token.password.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
