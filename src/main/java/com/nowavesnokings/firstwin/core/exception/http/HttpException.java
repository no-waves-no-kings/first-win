package com.nowavesnokings.firstwin.core.exception.http;

import lombok.Getter;

/**
 * @author ssx
 * @version V1.0
 * @className HttpException
 * @description http异常处理类
 * @date 2020-12-14 16:19
 * @since 1.8
 */
@Getter
public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 4578856763470422120L;
    protected Integer code;
    protected Integer httpStatus;
}
