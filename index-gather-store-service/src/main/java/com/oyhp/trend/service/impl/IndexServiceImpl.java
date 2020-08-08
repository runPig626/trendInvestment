package com.oyhp.trend.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oyhp.trend.model.Index;
import com.oyhp.trend.service.IndexService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 指数服务实现类
 * 用于收集第三方服务数据
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-08
 */
@Service
public class IndexServiceImpl implements IndexService{
    @Resource
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(defaultFallback = "thirdPartNotConnected")
    public List<Index> fetchIndicesFromThirdPart(){
        String indexDataUrl = "http://127.0.0.1:8090/indexes/codes.json";
        List<Map> tempIndexes = restTemplate.getForObject(indexDataUrl,List.class);
        return map2Index(tempIndexes);
    }

    /**
     * 第三方服务未启动时，断熔方法
     * @return 无效Index
     */
    public List<Index> thirdPartNotConnected(){
        Index index= new Index();
        index.setCode("000000");
        index.setName("无效指数代码");
        return Collections.singletonList(index);
    }

    /**
     * 将map指数转换成Index模型
     * @param tempIndexes 第三方服务指数
     * @return
     */
    private List<Index> map2Index(List<Map> tempIndexes) {
        List<Index> indexes = tempIndexes.stream().map(this::getIndex)
                .collect(Collectors.toList());

        return indexes;
    }

    /**
     * 用于Stream，将Map指数映射成Index
     * @param map 第三方服务指数
     * @return
     */
    private Index getIndex(Map map){
        Index index = new Index();
        index.setCode(map.get("code").toString());
        index.setName(map.get("name").toString());
        return index;
    }
}
