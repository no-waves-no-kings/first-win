package com.nowavesnokings.firstwin.core.aop;

import com.nowavesnokings.firstwin.core.response.UnifyResponse;
import com.nowavesnokings.firstwin.properties.UnifyResponseCodeProperties;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ssx
 * @version V1.0
 * @className RestResponseVOAspect
 * @description 处理controller层返回值为UnifyResponse的message值
 * @date 2021-01-04 15:05
 * @since 1.8
 */
@Aspect
@Component
public class ResultUnifyResponseAspect {
    @Autowired
    private UnifyResponseCodeProperties unifyResponseCodeProperties;

    @Pointcut(value = "execution(public * com.nowavesnokings.firstwin.api..*.*(..))")
    public void handleUnifyResponsePointcut() {

    }

    @AfterReturning(value = "handleUnifyResponsePointcut()", returning = "result")
    public void handleUnifyResponseAfter(UnifyResponse result) {
        Integer code = result.getCode();
        String resultMessage = result.getMessage();
        String message = unifyResponseCodeProperties.getMessage(code);
        if (StringUtils.isNotBlank(message) && StringUtils.isBlank(resultMessage)) {
            result.setMessage(message);
        }
    }

}
