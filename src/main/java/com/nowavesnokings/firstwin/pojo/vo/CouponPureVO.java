package com.nowavesnokings.firstwin.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ssx
 * @version V1.0
 * @className CouponPureVO
 * @description 优惠券PureVO
 * @date 2020-12-24 13:57
 * @since 1.8
 */
@Getter
@Setter
@ToString
public class CouponPureVO {
    /**
     * 优惠券id
     */
    private Long id;


    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

    /**
     * 满金额
     */
    private BigDecimal fullMoney;
    
    /**
     * 减金额
     */
    private BigDecimal minus;

    /**
     * 折扣率
     */
    private BigDecimal rate;

    /**
     * 是否全场券
     */
    private Boolean wholeStore;

    /**
     * 优惠券类型
     */
    private Integer type;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;
    
}
