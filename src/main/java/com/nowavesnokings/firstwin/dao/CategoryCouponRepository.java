package com.nowavesnokings.firstwin.dao;

import com.nowavesnokings.firstwin.pojo.model.CategoryCoupon;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className CategoryCouponRepository
 * @description 分类优惠券repository
 * @date 2021-01-16 18:00
 * @since 1.8
 */
@Repository
public interface CategoryCouponRepository extends BaseRepository<CategoryCoupon, Long> {
    /**
     * 根据优惠券id获取分类优惠券集合.
     *
     * @param couponIds the coupon ids
     * @return the category coupons by coupon id in
     */
    List<CategoryCoupon> getCategoryCouponsByCouponIdIn(List<Long> couponIds);
}
