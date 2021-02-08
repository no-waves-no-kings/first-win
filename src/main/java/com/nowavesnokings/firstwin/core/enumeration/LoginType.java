package com.nowavesnokings.firstwin.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ssx
 * @version V1.0
 * @className LoginType
 * @description 用户登录类型
 * @date 2020-12-14 15:11
 * @since 1.8
 */
@Getter
@AllArgsConstructor
public enum LoginType {
    /**
     * User wx login type.
     */
    USER_WX(0, "微信登录"),
    USER_TELEPHONE(1, "手机登录"),
    ;
    /**
     * 登录代码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String description;

}
