package com.oyhp.trend;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka注册服务
 * @author OYHP
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication
{
    public static void main( String[] args )
    {
        int port = 8761;
        // 服务端口未被占用
        Assert.isTrue(NetUtil.isUsableLocalPort(port), "端口"+ port +"被占用了，无法启动%n");
        // 启动服务
        new SpringApplicationBuilder(EurekaServerApplication.class)
                .properties("server.port=" + port)
                .run(args);
    }
}
