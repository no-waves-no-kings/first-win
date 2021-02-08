package com.nowavesnokings.firstwin.core.money;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

/**
 * @author ssx
 * @version V1.0
 * @className IMoneyDiscount
 * @description 折扣打折计算方式
 * @date 2021-01-22 16:24
 * @since 1.8
 */
public interface IMoneyDiscount {
    /**
     * 折扣计算.
     *
     * @param original   the original
     * @param rate       the rate
     * @param biFunction the bi function
     * @return the big decimal
     */
    static BigDecimal discount(BigDecimal original, BigDecimal rate, BinaryOperator<BigDecimal> biFunction) {
        return biFunction.apply(original, rate);
    }
}
