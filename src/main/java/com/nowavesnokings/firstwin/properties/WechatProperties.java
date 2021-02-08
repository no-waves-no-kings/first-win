package com.nowavesnokings.firstwin.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ssx
 * @version V1.0
 * @className WechatProperties
 * @description 微信登录配置类
 * @date 2020-12-15 16:44
 * @since 1.8
 */
@Setter
@Getter
public class WechatProperties {
    /**
     * 小程序id.
     */
    private String appid;

    /**
     * 小程序密钥
     */
    private String appSecret;

    /**
     * 获取openid的url
     */
    private String code2session;

}
