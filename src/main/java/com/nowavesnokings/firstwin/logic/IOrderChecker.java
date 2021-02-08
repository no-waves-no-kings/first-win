package com.nowavesnokings.firstwin.logic;

import com.nowavesnokings.firstwin.pojo.model.OrderSku;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className IOrderChecker
 * @description 订单校验器
 * @date 2021-01-25 9:13
 * @since 1.8
 */
public interface IOrderChecker {
    /**
     * 是否通过验证.
     */
    void isOk();

    /**
     * 获取头商品标题.
     *
     * @return the leader title
     */
    String getLeaderTitle();

    /**
     * 获取头商品图片.
     *
     * @return the leader img
     */
    String getLeaderImg();

    /**
     * 获取订单商品列表.
     *
     * @return the order skus
     */
    List<OrderSku> getOrderSkus();

    /**
     * 获取订单数量.
     *
     * @return the order count
     */
    Integer getOrderCount();

}
