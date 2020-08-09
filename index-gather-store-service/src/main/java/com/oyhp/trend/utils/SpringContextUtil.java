package com.oyhp.trend.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContext上下文工具类，用来refresh bean
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-08
 */
@Component
public class SpringContextUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 返回重新加载过的容器bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
