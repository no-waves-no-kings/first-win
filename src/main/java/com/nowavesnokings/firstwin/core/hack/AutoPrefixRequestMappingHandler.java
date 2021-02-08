package com.nowavesnokings.firstwin.core.hack;

import com.nowavesnokings.firstwin.properties.FirstwinProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author ssx
 * @version V1.0
 * @className AutoPrefixRequestMappingInfoHandler
 * @description 自动拼接request请求路径
 * @date 2020-12-17 9:24
 * @since 1.8
 */
@Component
public class AutoPrefixRequestMappingHandler extends RequestMappingHandlerMapping {
    /**
     * The Firstwin properties.
     */
    @Autowired
    private FirstwinProperties firstwinProperties;

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        //获取当前方法的全路径，根据配置的包路径，组装成新的requestMapping
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        if (mappingInfo != null) {
            String prefixName = getPrefixName(method);
            if (StringUtils.isNotBlank(prefixName)) {
                mappingInfo = RequestMappingInfo.paths(prefixName).build().combine(mappingInfo);
            }
        }
        return mappingInfo;
    }

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return super.isHandler(beanType);
    }

    /**
     * 获得requestMapping所在包前缀名.
     *
     * @param method the method
     * @return the prefix name
     */
    private String getPrefixName(Method method) {
        String packageName = method.getDeclaringClass().getPackage().getName();
        if (StringUtils.startsWith(packageName, firstwinProperties.getBaseUrlPackage())) {
            String prefixName = packageName.replaceAll(firstwinProperties.getBaseUrlPackage(), "");
            return prefixName.replace(".", "/");
        }
        return null;
    }
}
