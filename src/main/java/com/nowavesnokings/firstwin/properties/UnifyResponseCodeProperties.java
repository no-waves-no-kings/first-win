package com.nowavesnokings.firstwin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ssx
 * @version V1.0
 * @className UnifyReponseCodeProperties
 * @description 统一回复代码配置类
 * @date 2020-12-14 16:40
 * @since 1.8
 */
@PropertySource(value = "classpath:config/unifyResponseCode.yml", encoding = "UTF-8")
@ConfigurationProperties(prefix = "firstwin.err")
@Setter
@Getter
@Component
public class UnifyResponseCodeProperties {
    /**
     * The Codes.
     */
    private Map<Integer, String> codes;

    /**
     * 获取错误信息.
     *
     * @param code the code
     * @return the message
     */
    public String getMessage(Integer code) {
        return this.codes.get(code);
    }
}
