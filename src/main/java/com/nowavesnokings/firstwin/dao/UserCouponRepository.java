package com.nowavesnokings.firstwin.dao;

import com.nowavesnokings.firstwin.pojo.model.UserCoupon;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ssx
 * @version V1.0
 * @className UserCouponRepository
 * @description 用户优惠券repository
 * @date 2020-12-31 14:25
 * @since 1.8
 */
@Repository
public interface UserCouponRepository extends BaseRepository<UserCoupon, Long> {
    /**
     * 根据优惠券id和用户id看是否领取过该优惠券.
     *
     * @param cid the cid
     * @param uid the uid
     * @return the user coupon by coupon id and user id
     */
    Optional<UserCoupon> getUserCouponByCouponIdAndUserId(Long cid, Long uid);

    /**
     * 根据优惠券id和用户id及优惠券领取状态看是否领取过该优惠券.
     *
     * @param cid    the cid
     * @param uid    the uid
     * @param status the status
     * @return the user coupon by coupon id and user id and status
     */
    Optional<UserCoupon> getUserCouponByCouponIdAndUserIdAndStatus(Long cid, Long uid, Integer status);
}
