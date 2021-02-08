package com.nowavesnokings.firstwin.logic;

import com.nowavesnokings.firstwin.pojo.bo.SkuOrderBO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className IcouponChecker
 * @description 优惠券检查器
 * @date 2021-01-20 11:23
 * @since 1.8
 */
public interface ICouponChecker {
    /**
     * 根据用户id和优惠券id初始化检查器.
     *
     */
    void isOk();

    /**
     * 校验最终产品价格是否正确.
     *
     * @param orderFinalTotalPrice the order final total price
     * @param serverTotalPrice     the server total price
     * @return the boolean
     */
    void checkFinalTotalPrice(BigDecimal orderFinalTotalPrice, BigDecimal serverTotalPrice);

    /**
     * 验证该优惠券能否使用.
     *
     * @param skuPureDTOs      the sku pure dt os
     * @param serverTotalPrice the server total price
     * @return the boolean
     */
    Boolean canBeUsed(List<SkuOrderBO> skuPureDTOs, BigDecimal serverTotalPrice);

}
