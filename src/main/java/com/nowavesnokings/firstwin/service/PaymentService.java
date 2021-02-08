package com.nowavesnokings.firstwin.service;

import com.nowavesnokings.firstwin.pojo.vo.UnifyPaymentVO;

/**
 * @author ssx
 * @version V1.0
 * @className PaymentService
 * @description
 * @date 2021-02-05 13:12
 * @since 1.8
 */
public interface PaymentService {
    /**
     * 支付预处理.
     *
     * @param orderId the order id
     * @return the unify payment vo
     */
    UnifyPaymentVO prepayHandle(Long orderId);
}
