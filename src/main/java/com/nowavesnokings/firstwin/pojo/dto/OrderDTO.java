package com.nowavesnokings.firstwin.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className OrderDTO
 * @description 订单传输对象
 * @date 2021-01-17 15:15
 * @since 1.8
 */
@Setter
@Getter
@ToString
@ApiModel(value = "订单传输对象", description = "生成订单传输对象")
public class OrderDTO {
    /**
     * 原价
     */
    @DecimalMin(value = "0.00", message = "{decimal.outRange}")
    @DecimalMax(value = "99999999.99", message = "{decimal.outRange}")
    @ApiModelProperty(name = "totalPrice", value = "原价", required = true)
    private BigDecimal totalPrice;

    /**
     * 商品价格
     */
    @DecimalMin(value = "0.00", message = "{decimal.outRange}")
    @DecimalMax(value = "99999999.99", message = "{decimal.outRange}")
    @ApiModelProperty(name = "finalTotalPrice", value = "总价", required = true)
    private BigDecimal finalTotalPrice;

    /**
     * 优惠id
     */
    @ApiModelProperty(name = "couponId", value = "优惠券id", required = false)
    private Long couponId;

    /**
     * sku集合
     */
    @Valid
    @ApiModelProperty(name = "skuPureDTOs", value = "sku集合")
    private List<SkuPureDTO> skuPureDTOs;

    /**
     * 订单地址对象
     */
    @Valid
    @ApiModelProperty(name = "orderAddressDTO", value = "订单地址对象")
    private OrderAddressDTO orderAddressDTO;

}
