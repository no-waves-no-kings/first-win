package com.nowavesnokings.firstwin.service.impl;

import com.nowavesnokings.firstwin.core.concurrent.local.LocalUser;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.ForbiddenException;
import com.nowavesnokings.firstwin.pojo.model.Order;
import com.nowavesnokings.firstwin.pojo.vo.UnifyPaymentVO;
import com.nowavesnokings.firstwin.service.OrderService;
import com.nowavesnokings.firstwin.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ssx
 * @version V1.0
 * @className WxPaymentServiceImpl
 * @description 微信支付Service
 * @date 2021-02-05 13:13
 * @since 1.8
 */
@Service
public class WxPaymentServiceImpl implements PaymentService {
    @Autowired
    private OrderService orderService;

    /**
     * 支付预处理.
     *
     * @param orderId the order id
     * @return the unify payment vo
     */
    @Override
    public UnifyPaymentVO prepayHandle(Long orderId) {
        Long uid = LocalUser.getUser().getId();
        Order order = this.orderService.getOrderByIdAndUserId(orderId, uid);
        //检测订单是否超出失效期
        Boolean expired = order.canCancel();
        if (expired) {
            throw new ForbiddenException(UnifyResponseCode.ORDER_OUT_OF_DATE.getCode());
        }
        return null;
    }
}
