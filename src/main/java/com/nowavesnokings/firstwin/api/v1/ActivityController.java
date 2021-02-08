package com.nowavesnokings.firstwin.api.v1;

import com.nowavesnokings.firstwin.pojo.vo.ActivityCouponsPureVO;
import com.nowavesnokings.firstwin.pojo.vo.ActivityPureVO;
import com.nowavesnokings.firstwin.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssx
 * @version V1.0
 * @className ActivityController
 * @description 活动控制器
 * @date 2020-12-23 14:42
 * @since 1.8
 */
@RestController
@RequestMapping(value = "activity")
@Api(value = "活动相关接口", tags = {"用于获取活动相关信息的接口"})
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    /**
     * 根据活动名获取活动.
     *
     * @param name the name
     * @return the activity by name
     */
    @GetMapping(value = "/name/{name}")
    @ApiOperation(value = "根据活动名获取活动信息", httpMethod = "GET", notes = "根据活动名获取活动基本信息")
    public ActivityPureVO getActivityByName(@ApiParam(name = "name", value = "活动名", required = true)@PathVariable(value = "name") String name) {
        return activityService.getActivityByName(name);
    }

    /**
     * 根据活动名获取优惠券.
     *
     * @param name the name
     * @return the activity coupons by name
     */
    @GetMapping(value = "/name/{name}/with_coupon")
    @ApiOperation(value = "根据活动名获取优惠券", notes = "根据活动名获取活动及优惠券具体信息", httpMethod = "GET")
    public ActivityCouponsPureVO getActivityCouponsByName(@ApiParam(name = "name", value = "活动名") @PathVariable String name) {
        return activityService.getActivityCouponsByName(name);
    }
}
