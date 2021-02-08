package com.nowavesnokings.firstwin.pojo.dto;

import com.nowavesnokings.firstwin.core.annotation.TokenPassword;
import com.nowavesnokings.firstwin.core.enumeration.LoginType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * The type Token dto.
 *
 * @author ssx
 * @version V1.0
 * @className TokenDTO
 * @description 令牌传输对象
 * @date 2020 -12-14 14:58
 * @since 1.8
 */
@Getter
@Setter
@ToString
public class TokenGetDTO {
    /**
     * 账户
     */
    @NotBlank(message = "{account.NotBlank.message}")
    private String account;

    /**
     * 密码
     */
    @TokenPassword
    private String password;

    /**
     * 登录类型
     */
    private LoginType loginType;
}
