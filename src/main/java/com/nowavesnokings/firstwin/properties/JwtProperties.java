package com.nowavesnokings.firstwin.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ssx
 * @version V1.0
 * @className JwtProperties
 * @description jwt属性配置类
 * @date 2020-12-14 17:13
 * @since 1.8
 */
@Setter
@Getter
public class JwtProperties {
    /**
     * 密钥.
     */
    private String secret;
    /**
     * 过期时间.
     */
    private Integer expiredTime;
    /**
     * jwtToken前缀
     */
    private String tokenPrefix;
}
