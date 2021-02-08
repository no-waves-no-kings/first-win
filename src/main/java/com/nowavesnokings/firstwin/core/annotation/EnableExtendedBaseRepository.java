package com.nowavesnokings.firstwin.core.annotation;

import com.nowavesnokings.firstwin.config.JpaQueryFactoryConfig;
import com.nowavesnokings.firstwin.dao.extended.ExtendedBaseRepositoryFactoryBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ssx
 * @version V1.0
 * @className EnableExtendedBaseRepository
 * @description 允许扩展repostory注解
 * @date 2021-01-14 13:59
 * @since 1.8
 */
@Import(value = JpaQueryFactoryConfig.class)
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableJpaRepositories(repositoryFactoryBeanClass = ExtendedBaseRepositoryFactoryBean.class)
public @interface EnableExtendedBaseRepository {
}
