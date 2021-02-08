package com.nowavesnokings.firstwin.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author ssx
 * @version V1.0
 * @className AddressDTO
 * @description
 * @date 2021-01-17 16:21
 * @since 1.8
 */
@Getter
@Setter
@ToString
@ApiModel(value = "收货地址", description = "收货地址对象")
public class OrderAddressDTO {
    /**
     * 用户名
     */
    @NotBlank(message = "{address.username.notBlank.message}")
    @ApiModelProperty(name = "username", value = "收件人", required = true)
    private String username;

    /**
     * 联系方式
     */
    @NotBlank(message = "{mobile.notBlank.message}")
    @ApiModelProperty(name = "mobile", value = "联系方式", required = true)
    private String mobile;

    /**
     * 省
     */
    @NotBlank(message = "{address.province.notBlank.message}")
    @ApiModelProperty(name = "province", value = "省", required = true)
    private String province;

    /**
     * 市
     */
    @NotBlank(message = "{address.city.notBlank.message}")
    @ApiModelProperty(name = "city", value = "市", required = true)
    private String city;

    /**
     * 区域
     */
    @NotBlank(message = "{address.country.notBlank.message}")
    @ApiModelProperty(name = "country", value = "区域", required = true)
    private String country;

    /**
     * 具体地址
     */
    @NotBlank(message = "{address.detail.notBlank.message}")
    @ApiModelProperty(name = "detail", value = "详细地址", required = true)
    private String detail;

    /**
     * 国家编码
     */
    @ApiModelProperty(name = "nationalCode", value = "国家编码", required = false)
    private String nationalCode;

    /**
     * 邮政编码
     */
    @ApiModelProperty(name = "postalCode", value = "邮政编码", required = false)
    private String postalCode;
    
}
