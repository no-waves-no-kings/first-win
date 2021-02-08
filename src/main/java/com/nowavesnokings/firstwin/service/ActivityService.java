package com.nowavesnokings.firstwin.service;

import com.nowavesnokings.firstwin.pojo.vo.ActivityCouponsPureVO;
import com.nowavesnokings.firstwin.pojo.vo.ActivityPureVO;

/**
 * @author ssx
 * @version V1.0
 * @className ActivityService
 * @description 活动Service类
 * @date 2020-12-24 10:38
 * @since 1.8
 */
public interface ActivityService {
    /**
     * 根据活动名获取活动.
     *
     * @param name the name
     * @return the activity by name
     */
    ActivityPureVO getActivityByName(String name);

    /**
     * 根据活动名获取活动及优惠券信息.
     *
     * @param name the name
     * @return the activity coupons by name
     */
    ActivityCouponsPureVO getActivityCouponsByName(String name);
}
