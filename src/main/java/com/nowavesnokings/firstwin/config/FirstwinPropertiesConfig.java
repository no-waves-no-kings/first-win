package com.nowavesnokings.firstwin.config;

import com.nowavesnokings.firstwin.properties.FirstwinProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ssx
 * @version V1.0
 * @className FirstwinPropertiesConfig
 * @description 配置文件中属性配置类
 * @date 2020-12-17 10:11
 * @since 1.8
 */
@Configuration
@EnableConfigurationProperties(value = {FirstwinProperties.class})
public class FirstwinPropertiesConfig {
}
