package com.nowavesnokings.firstwin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowavesnokings.firstwin.core.enumeration.UnifyResponseCode;
import com.nowavesnokings.firstwin.core.exception.http.ServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ssx
 * @version V1.0
 * @className GenericAndJson
 * @description 公共json转化类
 * @date 2021-02-02 12:35
 * @since 1.8
 */
@Component
@Slf4j
public class GenericAndJson {
    private static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        GenericAndJson.objectMapper = objectMapper;
    }

    public static <T> String objectToJson(T t) {
        try {
            return GenericAndJson.objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.error("objectToJson异常: {}", ExceptionUtils.getStackTrace(e));
            throw new ServerErrorException(UnifyResponseCode.UNKNOWN_ERROR.getCode());
        }
    }

    public static <T> T jsonToObject(String json, TypeReference<T> tTypeReference) {
        if (json == null) {
            return null;
        }
        try {
            return GenericAndJson.objectMapper.readValue(json, tTypeReference);
        } catch (JsonProcessingException e) {
            log.error("jsonToObject异常: {}", ExceptionUtils.getStackTrace(e));
            throw new ServerErrorException(UnifyResponseCode.UNKNOWN_ERROR.getCode());
        }
    }
}
