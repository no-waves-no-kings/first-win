package com.nowavesnokings.firstwin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowavesnokings.firstwin.core.interceptor.PermissionInterceptor;
import com.nowavesnokings.firstwin.core.serializer.NullHandlerSerializerModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ssx
 * @version V1.0
 * @className WebMvcConfig
 * @description webmvc配置类
 * @date 2020-12-16 14:39
 * @since 1.8
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionInterceptor);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().filter(c -> c instanceof MappingJackson2HttpMessageConverter)
                           .map(c -> (MappingJackson2HttpMessageConverter)c)
                           .forEach(c -> {
                               ObjectMapper mapper = new ObjectMapper();
                               mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new NullHandlerSerializerModifier()));
                               c.setObjectMapper(mapper);
                           });
    }
}
