package com.nowavesnokings.firstwin.util;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ssx
 * @version V1.0
 * @className DozerUtils
 * @description 对象转化工具类
 * @date 2020-12-24 13:15
 * @since 1.8
 */
public class DozerUtils {
    /**
     * Instantiates a new Dozer utils.
     */
    private DozerUtils() {

    }

    /**
     * Convert bean t.
     *
     * @param <T>    the type parameter
     * @param <S>    the type parameter
     * @param bean   the bean
     * @param tClass the t class
     * @return the t
     */
    public static <T, S> T convertBean(S bean, Class<T> tClass) {
        if (Objects.isNull(tClass)) {
            throw new IllegalArgumentException();
        }
        if (Objects.isNull(bean)) {
            return null;
        }
        return BeanHolder.MAPPER.getMapper().map(bean, tClass);
    }

    /**
     * Convert list bean list.
     *
     * @param <T>    the type parameter
     * @param <S>    the type parameter
     * @param beans  the beans
     * @param tClass the t class
     * @return the list
     */
    public static <T, S> List<T> convertListBean(List<S> beans, Class<T> tClass) {
        if (Objects.isNull(tClass)) {
            throw new IllegalArgumentException();
        }
        return Optional
                .of(beans).orElse(new ArrayList<>())
                .stream().map(bean -> convertBean(bean, tClass))
                .collect(Collectors.toList());
    }

    /**
     * 单例实现DozerBeanMapperBuilder
     */
    private enum BeanHolder {
        /**
         * Mapper bean holder.
         */
        MAPPER;
        /**
         * The Mapper.
         */
        private final Mapper mapper;

        /**
         * Instantiates a new Bean holder.
         */
        BeanHolder() {
            this.mapper = DozerBeanMapperBuilder.buildDefault();
        }

        /**
         * Gets mapper.
         *
         * @return the mapper
         */
        public Mapper getMapper() {
            return this.mapper;
        }
    }
}
