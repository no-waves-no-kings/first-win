package com.nowavesnokings.firstwin.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className ActivityCouponsPureVO
 * @description 活动及优惠券VO对象
 * @date 2020-12-24 13:56
 * @since 1.8
 */
@Setter
@Getter
@ToString
public class ActivityCouponsPureVO extends ActivityPureVO {
    private List<CouponPureVO> coupons;
}
