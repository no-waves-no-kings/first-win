package com.nowavesnokings.firstwin.util;

import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ssx
 * @version V1.0
 * @className RequestUtils
 * @description 请求工具类
 * @date 2021-01-04 13:01
 * @since 1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtils {
    public static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
        return servletRequestAttributes != null ? servletRequestAttributes.getRequest() : null;
    }

    public static String getSimpleRequest() {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        if (httpServletRequest == null) {
            return null;
        }
        return Strings.lenientFormat("%s %s", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
    }
}
