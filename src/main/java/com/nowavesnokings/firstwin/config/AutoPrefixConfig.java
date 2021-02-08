package com.nowavesnokings.firstwin.config;

import com.nowavesnokings.firstwin.core.hack.AutoPrefixRequestMappingHandler;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author ssx
 * @version V1.0
 * @className AutoPrefixConfig
 * @description 自动配置requestMapping前缀配置类
 * @date 2020-12-17 11:58
 * @since 1.8
 */
@Configuration
public class AutoPrefixConfig implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new AutoPrefixRequestMappingHandler();
    }
}
