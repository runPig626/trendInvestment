package com.oyhp.trend;

import brave.sampler.Sampler;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NetUtil;
import com.oyhp.trend.utils.ArgsUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 * @author OYHP
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class TrendTradingBackTestServiceApplication
{
    public static void main( String[] args )
    {
        int port = 0;
        int defaultPort = 8051;
        int eurekaServerPort = 8761;

        Assert.isFalse(NetUtil.isUsableLocalPort(eurekaServerPort),
                "检查到端口"+ eurekaServerPort +" 未启用，判断 eureka 服务器没有启动，本服务无法使用，故退出%n");

        // 是否以参数启动
        port = ArgsUtil.checkArgsPort(args, port);

        // 交互式端口启动
        Future<Integer> future = ArgsUtil.startApplicationWithInputPort(port, defaultPort);
        try{
            port=future.get(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e){
            port = defaultPort;
        }

        // 端口未被占用
        Assert.isTrue(NetUtil.isUsableLocalPort(port),
                "端口" + port +"被占用了，无法启动%n");

        new SpringApplicationBuilder(TrendTradingBackTestServiceApplication.class).properties("server.port=" + port).run(args);
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
