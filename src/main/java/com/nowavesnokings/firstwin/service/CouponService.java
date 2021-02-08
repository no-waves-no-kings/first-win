package com.nowavesnokings.firstwin.service;

import com.nowavesnokings.firstwin.pojo.model.Coupon;
import com.nowavesnokings.firstwin.pojo.vo.CouponCategoryPureVO;
import com.nowavesnokings.firstwin.pojo.vo.CouponPureVO;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className CouponService
 * @description 优惠券Service
 * @date 2020-12-25 9:59
 * @since 1.8
 */
public interface CouponService extends CurdService<Coupon, Long> {
    /**
     * 根据分类id获取优惠券.
     *
     * @param cid the cid
     * @return the list
     */
    List<CouponPureVO> listCouponsByCategory(Long cid);

    /**
     * 获取全局优惠券.
     *
     * @return the list
     */
    List<CouponPureVO> listWholeStoreCoupons();

    /**
     * 领取优惠券.
     *
     * @param uid the uid
     * @param cid the cid
     * @return the boolean
     */
    Boolean collectCoupon(Long uid, Long cid);

    /**
     * 获取可用的优惠券列表.
     *
     * @param uid the uid
     * @return the list
     */
    List<CouponPureVO> listAvailableCoupon(Long uid);

    /**
     * 获取已经使用的优惠券列表.
     *
     * @param uid the uid
     * @return the list
     */
    List<CouponPureVO> listAlreadyUseCoupon(Long uid);

    /**
     * 获取已经过期的优惠券列表.
     *
     * @param uid the uid
     * @return the list
     */
    List<CouponPureVO> listOutDateCoupon(Long uid);

    /**
     * List available coupon with category list.
     *
     * @param uid the uid
     * @return the list
     */
    List<CouponCategoryPureVO> listAvailableCouponWithCategory(Long uid);
}
