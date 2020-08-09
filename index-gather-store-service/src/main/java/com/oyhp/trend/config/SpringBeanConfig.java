package com.oyhp.trend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * bean配置，用于上下文的bean注入
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
@Configuration
public class SpringBeanConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
