package com.nowavesnokings.firstwin.dao;

import com.nowavesnokings.firstwin.pojo.model.Order;
import org.springframework.stereotype.Repository;

/**
 * @author ssx
 * @version V1.0
 * @className OrderRepository
 * @description 订单repository
 * @date 2021-01-17 18:11
 * @since 1.8
 */
@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {
}
