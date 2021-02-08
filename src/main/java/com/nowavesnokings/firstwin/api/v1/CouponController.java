package com.nowavesnokings.firstwin.api.v1;

import com.nowavesnokings.firstwin.core.annotation.ScopeLevel;
import com.nowavesnokings.firstwin.core.concurrent.local.LocalUser;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.enumeration.UserCouponStatusType;
import com.nowavesnokings.firstwin.core.exception.http.NotFoundException;
import com.nowavesnokings.firstwin.core.response.CreatedResponse;
import com.nowavesnokings.firstwin.pojo.model.User;
import com.nowavesnokings.firstwin.pojo.vo.CouponCategoryPureVO;
import com.nowavesnokings.firstwin.pojo.vo.CouponPureVO;
import com.nowavesnokings.firstwin.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className CouponController
 * @description 优惠券控制器
 * @date 2020-12-25 9:18
 * @since 1.8
 */
@RestController
@RequestMapping(value = "coupon")
@Api(value = "优惠券接口", tags = {"处理优惠券相关接口"})
public class CouponController {
    /**
     * The Coupon service.
     */
    @Autowired
    private CouponService couponService;

    /**
     * 通过分类获取优惠券信息.
     *
     * @param cid the cid
     * @return the list
     */
    @GetMapping(value = "/by/category/{cid}")
    @ApiOperation(value = "通过分类获取优惠券信息", notes = "通过分类id获取优惠券列表")
    public List<CouponPureVO> listCouponsByCategory(@PathVariable(name = "cid") Long cid) {
        return couponService.listCouponsByCategory(cid);
    }

    /**
     * 获取全场优惠券.
     *
     * @return the list
     */
    @GetMapping("/whole_store")
    @ApiOperation(httpMethod = "GET", value = "获取全场优惠券", notes = "用于获取全场优惠券")
    public List<CouponPureVO> listWholeStoreCoupons() {
        return couponService.listWholeStoreCoupons();
    }

    /**
     * 领取优惠券.
     *
     * @param cid the cid
     * @return the created response
     */
    @ScopeLevel
    @PostMapping("/collect/{cid}")
    @ApiOperation(httpMethod = "POST", value = "领取优惠券", notes = "根据优惠券id领取优惠券")
    public CreatedResponse<String> collectCoupon(@ApiParam(name = "cid", value = "优惠券id", required = true)
                                 @PathVariable(name = "cid") Long cid) {
        //获取用户信息
        User user = LocalUser.getUser();
        this.couponService.collectCoupon(user.getId(), cid);
        return new CreatedResponse<String>();
    }

    /**
     * 根据优惠券使用状态查询.
     *
     * @param status the status
     * @return the list
     */
    @ScopeLevel
    @GetMapping("/myself/by/status/{status}")
    @ApiOperation(httpMethod = "GET", value = "根据优惠券使用状态查询", notes = "根据优惠券使用状态查询优惠券列表")
    public List<CouponPureVO> listCouponsByStatus(@ApiParam(name = "status", value = "优惠券状态", required = true)
                                                      @PathVariable(name = "status") Integer status) {
        Long uid = LocalUser.getUser().getId();
        UserCouponStatusType typeByCode = UserCouponStatusType.getTypeByCode(status);
        List<CouponPureVO> couponPureVOs = null;
        switch (typeByCode) {
            case NO_USE:
                couponPureVOs = couponService.listAvailableCoupon(uid);
                break;
            case ALREADY_USE:
                couponPureVOs = couponService.listAlreadyUseCoupon(uid);
                break;
            case OUT_DATE:
                couponPureVOs = couponService.listOutDateCoupon(uid);
                break;
            default:
                throw new NotFoundException(UnifyResponseCode.INCORRECT_COUPON_STATUS.getCode());
        }
        return couponPureVOs;
    }

    /**
     * 获取用户可用携带分类信息优惠券列表.
     *
     * @return the list
     */
    @GetMapping("/myself/available/with_category")
    @ScopeLevel
    @ApiOperation(httpMethod = "GET", value = "获取用户可用携带分类信息优惠券列表", notes = "获取用户可用携带分类信息优惠券列表")
    public List<CouponCategoryPureVO> listAvailableCouponWithCategory() {
        Long uid = LocalUser.getUser().getId();
        return couponService.listAvailableCouponWithCategory(uid);
    }
}
