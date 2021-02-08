package com.nowavesnokings.firstwin.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author ssx
 * @version V1.0
 * @className CouponType
 * @description 优惠券类型
 * @date 2021-01-21 15:13
 * @since 1.8
 */
@Getter
@AllArgsConstructor
public enum CouponType {
    /**
     * 满减券.
     */
    FULL_SUBTRACT_COUPON(1),

    /**
     * 满折扣券.
     */
    FULL_DISCOUNT_COUPON(2),

    /**
     * 全场减券.
     */
    WHOLE_SUBTRACT_COUPON(3),

    /**
     * 全场折扣券.
     */
    WHOLE_DISCOUNT_COUPON(4),
    ;
    /**
     * The Code.
     */
    private final Integer code;

    public static CouponType getCouponTypeByCode(Integer code) {
        return Stream.of(CouponType.values()).filter(couponType -> couponType.getCode().equals(code)).findFirst()
                .orElse(null);
    }
}
