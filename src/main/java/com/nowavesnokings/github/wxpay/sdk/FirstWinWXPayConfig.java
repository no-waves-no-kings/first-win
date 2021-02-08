package com.nowavesnokings.github.wxpay.sdk;

import java.io.InputStream;

/**
 * @author ssx
 * @version V1.0
 * @className FirstWinWXPayConfig
 * @description 微信支付配置类
 * @date 2021-02-05 16:09
 * @since 1.8
 */
public class FirstWinWXPayConfig extends WXPayConfig {
    /**
     * 获取 App ID
     *
     * @return App ID
     */
    @Override
    String getAppID() {
        return "wxc49f3615528950fa";
    }

    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    @Override
    String getMchID() {
        return null;
    }

    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    @Override
    String getKey() {
        return null;
    }

    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    @Override
    InputStream getCertStream() {
        return null;
    }

    /**
     * 获取WXPayDomain, 用于多域名容灾自动切换
     *
     * @return
     */
    @Override
    IWXPayDomain getWXPayDomain() {
        return null;
    }
}
