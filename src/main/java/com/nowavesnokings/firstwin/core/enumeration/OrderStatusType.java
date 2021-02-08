package com.nowavesnokings.firstwin.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author ssx
 * @version V1.0
 * @className OrderType
 * @description 订单状态
 * @date 2021-02-02 8:57
 * @since 1.8
 */
@Getter
@AllArgsConstructor
public enum OrderStatusType {
    /**
     * 全部.
     */
    ALL(0),

    /**
     * 未支付.
     */
    UN_PAID(1),

    /**
     * 待发货.
     */
    WAIT_SHIP(2),

    /**
     * 已发货.
     */
    DELIVERED(3),

    /**
     * 待取消.
     */
    CANCELED(4),

    /**
     * 已完成.
     */
    FINISHED(5),
    ;
    /**
     * The Code.
     */
    private final Integer code;

    /**
     * 根据code获取订单状态类型.
     *
     * @param code the code
     * @return the order status type by code
     */
    public static OrderStatusType getOrderStatusTypeByCode(Integer code) {
        return Stream.of(OrderStatusType.values()).filter(orderStatusType -> orderStatusType.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
