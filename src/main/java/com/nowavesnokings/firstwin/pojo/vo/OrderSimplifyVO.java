package com.nowavesnokings.firstwin.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ssx
 * @version V1.0
 * @className OrderSimplifyVO
 * @description
 * @date 2021-02-03 10:21
 * @since 1.8
 */
@Setter
@Getter
@ToString
public class OrderSimplifyVO {
    private Long id;

    private String orderNo;

    private BigDecimal totalPrice;

    private Integer totalCount;

    private BigDecimal finalTotalPrice;

    private BigDecimal postage;

    private String snapTitle;

    private String snapImg;

    private Integer status;

    private Date expiredTime;

    private Integer period;

}
