package com.nowavesnokings.firstwin.service;

import com.nowavesnokings.firstwin.pojo.dto.TokenGetDTO;

/**
 * @author ssx
 * @version V1.0
 * @className AuthenticationService
 * @description 用户验证
 * @date 2020 -12-14 14:52
 * @since 1.8
 */
public interface AuthenticationService {
    /**
     * 通过微信验证.
     *
     * @param tokenGetDTO the token dto
     * @return the string
     */
    String verifyByWx (TokenGetDTO tokenGetDTO);

    /**
     * 验证token.
     *
     * @param token the token
     * @return the boolean
     */
    Boolean verifyToken(String token);

}
