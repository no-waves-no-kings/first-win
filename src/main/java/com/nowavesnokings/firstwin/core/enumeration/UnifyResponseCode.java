package com.nowavesnokings.firstwin.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ssx
 * @version V1.0
 * @className UnifyResponseCode
 * @description 统一回复代码枚举类
 * @date 2020-12-14 16:57
 * @since 1.8
 */
@Getter
@AllArgsConstructor
public enum UnifyResponseCode {
    /**
     * Created success unify response code.
     */
    CREATED_SUCCESS(2),

    /**
     * Updated success unify response code.
     */
    UPDATED_SUCCESS(3),

    /**
     * Deleted success unify response code.
     */
    DELETED_SUCCESS(4),

    /**
     * Common params error unify response code.
     */
    COMMON_PARAMS_ERROR(10002),

    /**
     * Login type not found unify response code.
     */
    LOGIN_TYPE_NOT_FOUND(10003),

    /**
     * Token illegal or expire unify response code.
     */
    TOKEN_ILLEGAL_OR_EXPIRE(10004),

    /**
     * Unauthorized error unify response code.
     */
    UNAUTHORIZED_ERROR(10005),

    /**
     * User not found unify response code.
     */
    USER_NOT_FOUND(20001),

    /**
     * Get openid error unify response code.
     */
    GET_OPENID_ERROR(20004),

    /**
     * Category not found unify response code.
     */
    CATEGORY_NOT_FOUND(30001),

    /**
     * Activity not found unify response code.
     */
    ACTIVITY_NOT_FOUND(40001),

    /**
     * Coupon not found unify response code.
     */
    COUPON_NOT_FOUND(40004),

    /**
     * Coupon out date unify response code.
     */
    COUPON_OUT_DATE(40005),

    /**
     * Coupon already collect unify response code.
     */
    COUPON_ALREADY_COLLECT(40006),

    /**
     * Coupon not reach sill unify response code.
     */
    COUPON_NOT_REACH_SILL(40008),

    /**
     * Unsupport coupon type unify response code.
     */
    NOT_SUPPORT_COUPON_TYPE(40009),

    /**
     * Incorrect coupon status unify response code.
     */
    INCORRECT_COUPON_STATUS(40011),

    /**
     * Coupon write off failure unify response code.
     */
    COUPON_WRITE_OFF_FAILURE(40011),

    /**
     * Order have off shelf goods unify response code.
     */
    ORDER_HAVE_OFF_SHELF_GOODS(50002),

    /**
     * Order have no stock goods unify response code.
     */
    ORDER_HAVE_NO_STOCK_GOODS(50003),

    /**
     * Order over max buy number unify response code.
     */
    ORDER_OVER_MAX_BUY_NUMBER(50004),

    /**
     * Order amount not equal real amout unify response code.
     */
    ORDER_AMOUNT_NOT_EQUAL_REAL_AMOUNT(50005),

    /**
     * User have no coupon unify response code.
     */
    USER_HAVE_NO_COUPON(50006),

    /**
     * Order discount not equal real discout unify response code.
     */
    ORDER_DISCOUNT_NOT_EQUAL_REAL_DISCOUNT(50008),

    /**
     * Order not found unify response code.
     */
    ORDER_NOT_FOUND(50009),

    /**
     * 订单已过期.
     */
    ORDER_OUT_OF_DATE(50010),

    /**
     * Order un support order status unify response code.
     */
    ORDER_UN_SUPPORT_ORDER_STATUS(50013),

    /**
     * Unknown error unify response code.
     */
    UNKNOWN_ERROR(9999),
    ;
    /**
     * 错误码.
     */
    private final int code;
}
