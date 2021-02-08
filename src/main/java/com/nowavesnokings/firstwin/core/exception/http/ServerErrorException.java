package com.nowavesnokings.firstwin.core.exception.http;

import org.springframework.http.HttpStatus;

/**
 * @author ssx
 * @version V1.0
 * @className ServerException
 * @description 服务器异常
 * @date 2020-12-31 14:36
 * @since 1.8
 */
public class ServerErrorException extends HttpException {
    public ServerErrorException(Integer code) {
        this.code = code;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
