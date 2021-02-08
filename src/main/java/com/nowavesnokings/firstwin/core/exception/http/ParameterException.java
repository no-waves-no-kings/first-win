package com.nowavesnokings.firstwin.core.exception.http;

import org.springframework.http.HttpStatus;

/**
 * @author ssx
 * @version V1.0
 * @className ParameterException
 * @description 参数异常
 * @date 2020-12-15 17:27
 * @since 1.8
 */
public class ParameterException extends HttpException {
    private static final long serialVersionUID = 3235133829425127501L;

    public ParameterException(Integer code) {
        this.code = code;
        this.httpStatus = HttpStatus.BAD_REQUEST.value();
    }
}
