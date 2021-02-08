package com.nowavesnokings.firstwin.service;

import com.nowavesnokings.firstwin.core.enumeration.OrderStatusType;
import com.nowavesnokings.firstwin.logic.IOrderChecker;
import com.nowavesnokings.firstwin.pojo.bo.PageCounter;
import com.nowavesnokings.firstwin.pojo.dto.OrderDTO;
import com.nowavesnokings.firstwin.pojo.model.Order;
import com.nowavesnokings.firstwin.pojo.vo.OrderPureVO;
import com.nowavesnokings.firstwin.pojo.vo.OrderSimplifyVO;
import com.nowavesnokings.firstwin.pojo.vo.PagingDozerVO;

/**
 * @author ssx
 * @version V1.0
 * @className OrderService
 * @description 订单service
 * @date 2021-01-17 17:04
 * @since 1.8
 */
public interface OrderService {
    /**
     * 订单校验.
     *
     * @param uid      the uid
     * @param orderDTO the order dto
     * @return the order checker
     */
    IOrderChecker isOk(Long uid, OrderDTO orderDTO);

    /**
     * 生成订单.
     *
     * @param uid          the uid
     * @param orderDTO     the order dto
     * @param orderChecker the order checker
     * @return the long
     */
    Long placeOrder(Long uid, OrderDTO orderDTO, IOrderChecker orderChecker);

    /**
     * 获取未支付订单.
     *
     * @param pageCounter the page counter
     * @return the unpaid order
     */
    PagingDozerVO<Order, OrderSimplifyVO> getUnpaidOrder(PageCounter pageCounter);

    /**
     * 获取除未支付订单列表.
     *
     * @param pageCounter     the page counter
     * @param orderStatusType the order status type
     * @return the orders by status
     */
    PagingDozerVO<Order, OrderSimplifyVO> getOrdersByStatus(PageCounter pageCounter, OrderStatusType orderStatusType);

    /**
     * 获取订单详情.
     *
     * @param id the id
     * @return the order detail by id
     */
    OrderPureVO getOrderDetailById(Long id);

    /**
     * Gets order by id and user id.
     *
     * @param id  the id
     * @param uid the uid
     * @return the order by id and user id
     */
    Order getOrderByIdAndUserId(Long id, Long uid);
}
