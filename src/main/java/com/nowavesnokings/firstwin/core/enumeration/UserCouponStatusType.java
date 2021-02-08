package com.nowavesnokings.firstwin.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author ssx
 * @version V1.0
 * @className UserCouponStatusType
 * @description 用户领取优惠券状态
 * @date 2020-12-31 14:42
 * @since 1.8
 */
@Getter
@AllArgsConstructor
public enum UserCouponStatusType {
    /**
     * 未使用.
     */
    NO_USE(1),

    /**
     * 已使用.
     */
    ALREADY_USE(2),

    /**
     * 已过期.
     */
    OUT_DATE(3),
    ;
    /**
     * The Code.
     */
    private Integer code;

    /**
     * 根据code获取枚举.
     *
     * @param code the code
     * @return the type by code
     */
    public static UserCouponStatusType getTypeByCode(Integer code) {
        return Stream.of(UserCouponStatusType.values())
                .filter(userCouponStatusType ->
                        userCouponStatusType.getCode()
                                .equals(code)).findFirst().orElse(null);
    }
}
