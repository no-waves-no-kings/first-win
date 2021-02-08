package com.nowavesnokings.firstwin.core.advice;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.HttpException;
import com.nowavesnokings.firstwin.core.response.UnifyResponse;
import com.nowavesnokings.firstwin.properties.UnifyResponseCodeProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className GlobalExceptionAdice
 * @description 统一异常处理类
 * @date 2020-12-17 13:06
 * @since 1.8
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
    @Autowired
    private UnifyResponseCodeProperties unifyResponseCodeProperties;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(Exception e, HttpServletRequest request) {
        log.error("发生未知异常为: {}", ExceptionUtils.getStackTrace(e));
        return this.getUnifyResponse(UnifyResponseCode.UNKNOWN_ERROR.getCode(), unifyResponseCodeProperties.getMessage(UnifyResponseCode.UNKNOWN_ERROR.getCode()), request);
    }

    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpException e, HttpServletRequest request) {
        log.error("发生HttpException异常为: {}", ExceptionUtils.getStackTrace(e));
        UnifyResponse unifyResponse = this.getUnifyResponse(e.getCode(),unifyResponseCodeProperties.getMessage(e.getCode()), request);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatus());
        assert httpStatus != null;
        return new ResponseEntity<>(unifyResponse, httpHeaders, httpStatus);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR )
    public UnifyResponse handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        List<String> messages = Lists.newArrayList();
        e.getConstraintViolations().forEach(constraintViolation -> messages.add(constraintViolation.getMessage()));
        String joinMsg = Joiner.on(";").skipNulls().join(messages);
        return this.getUnifyResponse(UnifyResponseCode.COMMON_PARAMS_ERROR.getCode(), joinMsg, request);
    }

    private UnifyResponse getUnifyResponse(Integer code, String message, HttpServletRequest request) {
        String requestUrl = Strings.lenientFormat("%s %s", request.getMethod(), request.getRequestURI());
        return UnifyResponse.builder()
                .code(code)
                .message(message)
                .request(requestUrl).build();
    }
}
