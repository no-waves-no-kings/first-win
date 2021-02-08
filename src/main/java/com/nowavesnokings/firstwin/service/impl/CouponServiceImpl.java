package com.nowavesnokings.firstwin.service.impl;

import com.google.common.collect.Lists;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.enumeration.UserCouponStatusType;
import com.nowavesnokings.firstwin.core.exception.http.NotFoundException;
import com.nowavesnokings.firstwin.core.exception.http.ParameterException;
import com.nowavesnokings.firstwin.core.exception.http.ServerErrorException;
import com.nowavesnokings.firstwin.dao.ActivityRepository;
import com.nowavesnokings.firstwin.dao.CategoryCouponRepository;
import com.nowavesnokings.firstwin.dao.CategoryRepository;
import com.nowavesnokings.firstwin.dao.CouponRepository;
import com.nowavesnokings.firstwin.dao.UserCouponRepository;
import com.nowavesnokings.firstwin.pojo.model.Activity;
import com.nowavesnokings.firstwin.pojo.model.Category;
import com.nowavesnokings.firstwin.pojo.model.CategoryCoupon;
import com.nowavesnokings.firstwin.pojo.model.Coupon;
import com.nowavesnokings.firstwin.pojo.model.UserCoupon;
import com.nowavesnokings.firstwin.pojo.vo.CategoryPureVO;
import com.nowavesnokings.firstwin.pojo.vo.CouponCategoryPureVO;
import com.nowavesnokings.firstwin.pojo.vo.CouponPureVO;
import com.nowavesnokings.firstwin.service.CouponService;
import com.nowavesnokings.firstwin.util.CommonUtils;
import com.nowavesnokings.firstwin.util.DozerUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.nowavesnokings.firstwin.pojo.model.QCoupon.coupon;
import static com.nowavesnokings.firstwin.pojo.model.QUserCoupon.userCoupon;

/**
 * @author ssx
 * @version V1.0
 * @className CouponServiceImpl
 * @description 优惠券Service实现类
 * @date 2020-12-25 9:59
 * @since 1.8
 */
@Service
public class CouponServiceImpl extends AbstractBaseService<Coupon, Long, CouponRepository> implements CouponService{
    /**
     * The Coupon repository.
     */
    @Autowired
    private CouponRepository couponRepository;

    /**
     * The Category repository.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * The Activity repository.
     */
    @Autowired
    private ActivityRepository activityRepository;

    /**
     * The User coupon repository.
     */
    @Autowired
    private UserCouponRepository userCouponRepository;

    @Autowired
    private CategoryCouponRepository categoryCouponRepository;

    /**
     * 根据分类id获取优惠券.
     *
     * @param cid the cid
     * @return the list
     */
    @Override
    public List<CouponPureVO> listCouponsByCategory(Long cid) {
        //先用jpql实现
//        List<Coupon> coupons = couponRepository.listCouponsByCategory(cid, new Date());
        //通过命名规则实现
//        List<Coupon> coupons = couponRepository.getCouponsByCategoriesId(cid);
        //单表查询实现
        //根据分类id查询分类是否存在
        categoryRepository.getByIdAndOnlineTrue(cid)
                .orElseThrow(() -> new NotFoundException(UnifyResponseCode.CATEGORY_NOT_FOUND.getCode()));
        //单表操作 根据分类id查询该分类下所有优惠券
        List<Coupon> coupons = couponRepository.getCouponByCategoryCouponsCategoryId(cid);
        //获得这些优惠券的所有活动
        return getCouponActivityIds(coupons);
    }

    /**
     * 获取全局优惠券.
     *
     * @return the list
     */
    @Override
    public List<CouponPureVO> listWholeStoreCoupons() {
        List<Coupon> coupons = couponRepository.getCouponsByWholeStoreTrue();
        return getCouponActivityIds(coupons);
    }

    /**
     * 领取优惠券.
     *
     * @param uid  the id
     * @param cid the cid
     * @return the boolean
     */
    @Override
    public Boolean collectCoupon(Long uid, Long cid) {
        //判断优惠券是否存在
        Coupon coupon = couponRepository.findById(cid).orElseThrow(() -> new NotFoundException(UnifyResponseCode.CATEGORY_NOT_FOUND.getCode()));
        //判断优惠券领取是否过期
        Activity activity = activityRepository.getActivityByIdAndOnline(coupon.getActivityId(), true).orElseThrow(() ->
                new NotFoundException(UnifyResponseCode.ACTIVITY_NOT_FOUND.getCode()));
        Date now = new Date();
        boolean inLine = CommonUtils.isTimeInLine(now, activity.getStartTime(), activity.getEndTime());
        if (!inLine) {
            throw new ParameterException(UnifyResponseCode.COUPON_OUT_DATE.getCode());
        }
        //判断是否用户是否领取过该优惠券
        userCouponRepository.getUserCouponByCouponIdAndUserId(cid, uid)
                .ifPresent((userCoupon) -> {throw new ServerErrorException(UnifyResponseCode.COUPON_ALREADY_COLLECT.getCode());});
        //领取优惠券
        UserCoupon userCoupon = UserCoupon.builder().userId(uid)
                .couponId(cid)
                .status(UserCouponStatusType.NO_USE.getCode())
                .build();
        userCoupon.setCreateTime(now);
        userCouponRepository.save(userCoupon);
        return Boolean.TRUE;
    }

    /**
     * 获取可用的优惠券列表.
     *
     * @param uid the uid
     */
    @Override
    public List<CouponPureVO> listAvailableCoupon(Long uid) {
        //根据用户id 获取可用的优惠券id
        List<Long> couponIds = getNotUseCoupons(uid);
        if (CollectionUtils.isEmpty(couponIds)) {
            return Lists.newArrayList();
        }
        //根据用户有效的优惠券id集合，获取有用的优惠券
        Date now = new Date();
        List<Coupon> coupons = couponRepository.findAll(coupon.id.in(couponIds)
                .and(coupon.startTime.before(now)
                        .and(coupon.endTime.after(now))));
        return DozerUtils.convertListBean(coupons, CouponPureVO.class);
    }

    /**
     * 获取已经使用的优惠券列表.
     *
     * @param uid the uid
     * @return the list
     */
    @Override
    public List<CouponPureVO> listAlreadyUseCoupon(Long uid) {
        //根据用户id获取已经使用的优惠券列表
        List<UserCoupon> userCoupons = userCouponRepository.findAll(userCoupon.userId.eq(uid)
                .and(userCoupon.orderId.isNotNull())
                .and(userCoupon.status.eq(UserCouponStatusType.ALREADY_USE.getCode())));
        List<Long> couponIds = userCoupons.stream().map(UserCoupon::getCouponId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(couponIds)) {
            return Lists.newArrayList();
        }
        Date now = new Date();
        List<Coupon> coupons = couponRepository.findAll(coupon.id.in(couponIds)
                                                        .and(coupon.startTime.before(now)));
        return DozerUtils.convertListBean(coupons, CouponPureVO.class);
    }

    /**
     * 获取已经过期的优惠券列表.
     *
     * @param uid the uid
     * @return the list
     */
    @Override
    public List<CouponPureVO> listOutDateCoupon(Long uid) {
        List<UserCoupon> userCoupons = userCouponRepository.findAll(userCoupon.userId.eq(uid)
                .and(userCoupon.status.ne(UserCouponStatusType.ALREADY_USE.getCode())
                        .and(userCoupon.orderId.isNull())));
        List<Long> couponIds = userCoupons.stream().map(UserCoupon::getCouponId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(couponIds)) {
            return Lists.newArrayList();
        }
        Date now = new Date();
        List<Coupon> coupons = couponRepository.findAll(coupon.id.in(couponIds)
                                                        .and(coupon.endTime.before(now)));
        return DozerUtils.convertListBean(coupons, CouponPureVO.class);
    }

    /**
     * List available coupon with category list.
     *
     * @param uid the uid
     * @return the list
     */
    @Override
    public List<CouponCategoryPureVO> listAvailableCouponWithCategory(Long uid) {
        //获取有用的优惠券列表
        List<CouponPureVO> couponPureVOs = this.listAvailableCoupon(uid);
        if (CollectionUtils.isEmpty(couponPureVOs)) {
            return Lists.newArrayList();
        }
        List<CouponCategoryPureVO> couponCategoryPureVOs = DozerUtils.convertListBean(couponPureVOs, CouponCategoryPureVO.class);
        List<Long> couponIds = couponPureVOs.stream().map(CouponPureVO::getId).collect(Collectors.toList());
        //根据优惠券id获取分类信息
        List<CategoryCoupon> categoryCoupons = categoryCouponRepository.getCategoryCouponsByCouponIdIn(couponIds);
        if (CollectionUtils.isEmpty(categoryCoupons)) {
            return couponCategoryPureVOs;
        }
        Map<Long, List<Long>> couponIdMap = categoryCoupons.stream().collect(Collectors.groupingBy(CategoryCoupon::getCouponId, Collectors.mapping(CategoryCoupon::getCategoryId,Collectors.toList())));
        List<Long> categoryIds = categoryCoupons.stream().map(CategoryCoupon::getCategoryId).distinct().collect(Collectors.toList());

        List<Category> categories = categoryRepository.getCategoriesByIdIn(categoryIds);
        Map<Long, Category> categoryMap = categories.stream().collect(Collectors.toMap(Category::getId, category -> category));
       return couponCategoryPureVOs.stream()
               .peek(coupon -> {
            List<Category> categoryList = couponIdMap.get(coupon.getId()) != null ?couponIdMap.get(coupon.getId()).stream().map(categoryMap::get).collect(Collectors.toList())
                    : Lists.newArrayList();
            coupon.setCategories(DozerUtils.convertListBean(categoryList, CategoryPureVO.class));
       }).collect(Collectors.toList());
    }

    /**
     * 获取没有使用的优惠券id集合.
     *
     * @param uid the uid
     * @return the not use coupons
     */
    private List<Long> getNotUseCoupons(Long uid) {
        List<UserCoupon> userCoupons = userCouponRepository.findAll(userCoupon.userId.eq(uid)
                .and(userCoupon.status.eq(UserCouponStatusType.NO_USE.getCode())
                        .and(userCoupon.orderId.isNull())));
        return userCoupons.stream().map(UserCoupon::getCouponId).collect(Collectors.toList());
    }

    /**
     * Gets coupon activity ids.
     *
     * @param coupons the coupons
     * @return the coupon activity ids
     */
    private List<CouponPureVO> getCouponActivityIds(List<Coupon> coupons) {
        List<Long> activityIds = coupons.stream().map(Coupon::getActivityId).distinct().collect(Collectors.toList());
        Date now = new Date();
        List<Activity> activities = activityRepository.getActivitiesByIdInAndOnlineTrueAndStartTimeLessThanAndEndTimeGreaterThanEqual(activityIds, now, now);
        Map<Long, Activity> activityMap = activities.stream().collect(Collectors.toMap(Activity::getId, activity -> activity));
        List<Coupon> couponList = coupons.stream().filter(coupon -> activityMap.get(coupon.getActivityId()) != null).collect(Collectors.toList());
        return DozerUtils.convertListBean(couponList, CouponPureVO.class);
    }
}
