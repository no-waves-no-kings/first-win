package com.nowavesnokings.firstwin.core.response;

import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.util.ResponseUtils;
import org.springframework.http.HttpStatus;

/**
 * @author ssx
 * @version V1.0
 * @className CreatedResponse
 * @description 创建成功返回对象
 * @date 2021-01-04 11:25
 * @since 1.8
 */
public class CreatedResponse<T> extends UnifyResponse {
    private T data;

    public CreatedResponse() {
        super(UnifyResponseCode.CREATED_SUCCESS.getCode());
        ResponseUtils.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedResponse(T data) {
        super(UnifyResponseCode.CREATED_SUCCESS.getCode());
        this.data = data;
        ResponseUtils.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedResponse(Integer code) {
        super(code);
        ResponseUtils.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }
}
