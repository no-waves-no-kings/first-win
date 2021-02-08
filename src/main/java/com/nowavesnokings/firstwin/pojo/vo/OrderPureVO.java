package com.nowavesnokings.firstwin.pojo.vo;

import com.nowavesnokings.firstwin.pojo.model.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ssx
 * @version V1.0
 * @className OrderPureVO
 * @description
 * @date 2021-02-04 10:26
 * @since 1.8
 */
@Setter
@Getter
@ToString
public class OrderPureVO extends Order {
    private Integer period;
}
