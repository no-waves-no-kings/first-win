package com.nowavesnokings.firstwin.core.exception.http;

import org.springframework.http.HttpStatus;

/**
 * @author ssx
 * @version V1.0
 * @className ForbiddenException
 * @description 禁止访问异常
 * @date 2020-12-16 15:22
 * @since 1.8
 */
public class ForbiddenException extends HttpException {
    public ForbiddenException(Integer code) {
        this.code = code;
        this.httpStatus = HttpStatus.FORBIDDEN.value();
    }
}
