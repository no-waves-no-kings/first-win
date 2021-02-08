package com.nowavesnokings.firstwin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className FirstwinProperties
 * @description firstwin配置类
 * @date 2020-12-17 10:16
 * @since 1.8
 */
@ConfigurationProperties(prefix = "firstwin")
@Setter
@Getter
public class FirstwinProperties {
    /**
     * 基础请求包名
     */
    private String baseUrlPackage;

    private Integer defaultScope;

    private JwtProperties jwt;

    private WechatProperties wechat;

    private int orderBuyNumber;

    private int orderSingleNumber;

    private List<String> orderYear;

    private Integer orderOutDateLimit;


}
