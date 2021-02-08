package com.nowavesnokings.firstwin.core.response;

import com.nowavesnokings.firstwin.util.RequestUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ssx
 * @version V1.0
 * @className UnifyResponse
 * @description 异常统一回复
 * @date 2020-12-17 9:12
 * @since 1.8
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnifyResponse {
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回具体消息
     */
    private String message;

    /**
     * 请求方法和url
     */
    private String request;

    public UnifyResponse(Integer code) {
        this.code = code;
        this.request = RequestUtils.getSimpleRequest();
    }
}
