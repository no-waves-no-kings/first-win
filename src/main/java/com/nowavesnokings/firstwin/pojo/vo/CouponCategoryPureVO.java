package com.nowavesnokings.firstwin.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className CouponCategoryPureVO
 * @description 优惠券及分类视图对象
 * @date 2021-01-16 16:59
 * @since 1.8
 */
@Setter
@Getter
@ToString
public class CouponCategoryPureVO extends CouponPureVO {
    /**
     * 分类对象.
     */
    List<CategoryPureVO> categories;
}
