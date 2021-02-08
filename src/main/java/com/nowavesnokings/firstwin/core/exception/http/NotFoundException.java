package com.nowavesnokings.firstwin.core.exception.http;

import org.springframework.http.HttpStatus;

/**
 * @author ssx
 * @version V1.0
 * @className NotFoundException
 * @description 未发现404异常
 * @date 2020-12-14 16:23
 * @since 1.8
 */
public class NotFoundException extends HttpException {
    private static final long serialVersionUID = -205777618843682719L;

    public NotFoundException(Integer code) {
        this.code = code;
        this.httpStatus = HttpStatus.NOT_FOUND.value();
    }
}
