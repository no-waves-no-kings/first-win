package com.nowavesnokings.firstwin.dao;

import com.nowavesnokings.firstwin.pojo.model.Coupon;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className CouponRepository
 * @description 优惠券Repository对象
 * @date 2020-12-25 9:57
 * @since 1.8
 */
@Repository
public interface CouponRepository extends BaseRepository<Coupon, Long> {
    //    List<Coupon> getCouponsByCategoriesId(Long cid);

    /**
     * 根据分类id获取优惠券信息.
     *
     * @param id the id
     * @return the coupons by categories id
     */
    List<Coupon> getCouponByCategoryCouponsCategoryId(Long id);

    /**
     * 获取全局优惠券.
     *
     * @return the coupons by whole store true
     */
    List<Coupon> getCouponsByWholeStoreTrue();

    /**
     * 根据分类id获取优惠券(JPQL方式).
     *
     * @param cid the cid
     * @param now the now
     * @return the list
     */
//    @Query("SELECT\n" +
//            "\tc \n" +
//            "FROM\n" +
//            "\tCoupon c\n" +
//            "\tJOIN c.categories ca\n" +
//            "\tJOIN Activity a ON a.id = c.activityId \n" +
//            "WHERE\n" +
//            "\tca.id = :cid \n" +
//            "\tAND a.startTime <= :now AND a.endTime >= :now")
//    List<Coupon> listCouponsByCategory(Long cid, Date now);

}
