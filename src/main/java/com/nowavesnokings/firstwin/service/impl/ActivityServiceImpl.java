package com.nowavesnokings.firstwin.service.impl;

import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.NotFoundException;
import com.nowavesnokings.firstwin.dao.ActivityRepository;
import com.nowavesnokings.firstwin.pojo.model.Activity;
import com.nowavesnokings.firstwin.pojo.vo.ActivityCouponsPureVO;
import com.nowavesnokings.firstwin.pojo.vo.ActivityPureVO;
import com.nowavesnokings.firstwin.service.ActivityService;
import com.nowavesnokings.firstwin.util.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ssx
 * @version V1.0
 * @className ActivityServiceImpl
 * @description 活动Service类实现类
 * @date 2020-12-24 10:40
 * @since 1.8
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    /**
     * 根据活动名获取活动.
     *
     * @param name the name
     * @return the activity by name
     */
    @Override
    public ActivityPureVO getActivityByName(String name) {
        Activity activity = activityRepository.getActivityByNameAndOnlineTrue(name).orElseThrow(() -> new NotFoundException(UnifyResponseCode.ACTIVITY_NOT_FOUND.getCode()));
        return DozerUtils.convertBean(activity, ActivityPureVO.class);
    }

    /**
     * 根据活动名获取活动及优惠券信息.
     *
     * @param name the name
     * @return the activity coupons by name
     */
    @Override
    public ActivityCouponsPureVO getActivityCouponsByName(String name) {
        Activity activity = activityRepository.getActivityByNameAndOnlineTrue(name).orElseThrow(() -> new NotFoundException(UnifyResponseCode.ACTIVITY_NOT_FOUND.getCode()));
        return DozerUtils.convertBean(activity, ActivityCouponsPureVO.class);
    }
}
