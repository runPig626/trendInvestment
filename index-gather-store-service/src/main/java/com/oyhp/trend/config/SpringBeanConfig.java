package com.oyhp.trend.config;

import com.oyhp.trend.model.IndexData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Bean
    Map<String, List<IndexData>> indexDataMap(){
        return new HashMap<>(16);
    }
}
