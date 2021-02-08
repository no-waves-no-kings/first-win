package com.nowavesnokings.firstwin.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ssx
 * @version V1.0
 * @className SkuPureDTO
 * @description
 * @date 2021-01-17 16:00
 * @since 1.8
 */
@Setter
@Getter
@ToString
@ApiModel(value = "sku对象", description = "提交订单sku相关信息")
public class SkuPureDTO {
    /**
     * skuid
     */
    @ApiModelProperty(name = "id", value = "skuid", required = true)
    @NotNull(message = "{sku.id.notNull.message}")
    private Long id;

    /**
     * sku数量
     */
    @ApiModelProperty(name = "count", value = "sku数量", required = true)
    @Min(value = 1, message = "{sku.count.min.message}")
    private Integer count;

}
