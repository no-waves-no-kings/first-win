package com.nowavesnokings.firstwin.core.exception.http;

import org.springframework.http.HttpStatus;

/**
 * @author ssx
 * @version V1.0
 * @className UnauthorizedException
 * @description 用户未授权异常
 * @date 2020-12-16 15:26
 * @since 1.8
 */
public class UnauthorizedException extends HttpException {
    public UnauthorizedException(Integer code) {
        this.code = code;
        this.httpStatus = HttpStatus.UNAUTHORIZED.value();
    }
}
