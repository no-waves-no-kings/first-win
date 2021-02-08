package com.nowavesnokings.firstwin.core.annotation;

import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ssx
 * @version V1.0
 * @className ScopeLevel
 * @description 用户身份水平注解
 * @date 2020-12-16 14:58
 * @since 1.8
 */
@Documented
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ScopeLevel {
    int level() default 8;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
