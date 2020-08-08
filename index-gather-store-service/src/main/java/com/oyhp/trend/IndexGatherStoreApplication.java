package com.oyhp.trend;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NetUtil;
import com.oyhp.trend.utils.ArgsUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 收集存储基金指数应用
 * @author OYHP
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class IndexGatherStoreApplication
{
    public static void main( String[] args )
    {
        int port;
        int defaultPort = 8001;
        int eurekaServerPort = 8761;
        port = defaultPort ;

        // 注册服务器必须已开启
        Assert.isFalse(NetUtil.isUsableLocalPort(eurekaServerPort),
                "检查到端口%d 未启用，判断 eureka 服务器没有启动，本服务无法使用，故退出%n", eurekaServerPort);
        // 是否以参数启动
        port = ArgsUtil.checkArgsPort(args, port);
        // 第三方服务端口未被占用
        Assert.isTrue(NetUtil.isUsableLocalPort(port),
                "端口%d被占用了，无法启动%n", port);
        // 启动服务
        new SpringApplicationBuilder(IndexGatherStoreApplication.class).properties("server.port=" + port).run(args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
