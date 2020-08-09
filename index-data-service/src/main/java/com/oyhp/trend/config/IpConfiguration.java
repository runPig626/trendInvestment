package com.oyhp.trend.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * Ip端口号配置
 * 用于获取当前的端口号。因为这个微服务会做成集群，
 * 不同的微服务使用的是不同的端口号，可以通过获取并打印出来知道当前是哪个。
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
@Configuration
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent>{
    /** 服务端口 */
    private int serverPort;

    /** 应用端口监听 */
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    public int getServerPort(){
        return this.serverPort;
    }
}
