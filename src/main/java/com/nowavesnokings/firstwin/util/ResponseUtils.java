package com.nowavesnokings.firstwin.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ssx
 * @version V1.0
 * @className ResponseUtils
 * @description 返回工具类
 * @date 2021-01-04 11:12
 * @since 1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtils {
    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletResponse getHttpServletResponse() {
        ServletRequestAttributes requestAttributes = getRequestAttributes();
        return requestAttributes != null ? requestAttributes.getResponse() : null;
    }

    public static void setCurrentResponseHttpStatus(int httpStatus) {
        HttpServletResponse httpServletResponse = getHttpServletResponse();
        if (httpServletResponse != null) {
            httpServletResponse.setStatus(httpStatus);
        }
    }
}
