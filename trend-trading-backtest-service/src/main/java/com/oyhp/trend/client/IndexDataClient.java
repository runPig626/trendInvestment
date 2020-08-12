package com.oyhp.trend.client;

import com.oyhp.trend.model.IndexData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author OYHP
 */
@FeignClient(value = "INDEX-DATA-SERVICE", fallback = IndexDataClientFeignHystrix.class)
public interface IndexDataClient {

    /**
     * 获取index-data-service服务端调用
     * @param code
     * @return
     */
    @GetMapping("/index/data/{code}")
    List<IndexData> getIndexData(@PathVariable("code") String code);

}