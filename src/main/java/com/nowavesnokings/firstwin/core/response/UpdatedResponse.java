package com.nowavesnokings.firstwin.core.response;

import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.util.ResponseUtils;
import org.springframework.http.HttpStatus;

/**
 * @author ssx
 * @version V1.0
 * @className UpdatedResponse
 * @description 更新成功返回对象
 * @date 2021-01-04 16:28
 * @since 1.8
 */
public class UpdatedResponse extends UnifyResponse {
    public UpdatedResponse() {
        super(UnifyResponseCode.UPDATED_SUCCESS.getCode());
        ResponseUtils.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedResponse(Integer code) {
        super(code);
        ResponseUtils.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }
}
