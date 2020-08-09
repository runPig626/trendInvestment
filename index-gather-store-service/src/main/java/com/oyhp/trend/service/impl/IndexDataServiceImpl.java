package com.oyhp.trend.service.impl;

import cn.hutool.core.convert.Convert;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oyhp.trend.model.IndexData;
import com.oyhp.trend.service.IndexDataService;
import com.oyhp.trend.utils.SpringContextUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 指数代码数据服务实现类
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
@Service
@CacheConfig(cacheNames = "index_data")
public class IndexDataServiceImpl implements IndexDataService{

    @Resource
    private Map<String, List<IndexData>> indexDataMap;

    @Resource
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(defaultFallback = "thirdPartNotConnected")
    public List<IndexData> fresh(String code) {
        List<IndexData> indexDataList = fetchIndexDataListFromThirdPart(code);
        indexDataMap.put(code, indexDataList);
        IndexDataService indexDataService = SpringContextUtil.getBean(IndexDataServiceImpl.class);
        indexDataService.remove(code);
        return indexDataService.store(code);
    }

    /**
     * 根据指数code，获取第三服务指数数据
     * @param code
     * @return
     */
    private List<IndexData> fetchIndexDataListFromThirdPart(String code) {
        List<Map> tempMap = restTemplate.getForObject("http://127.0.0.1:8090/indexes/"+code+".json",List.class);
        return map2IndexData(tempMap);
    }

    private List<IndexData> map2IndexData(List<Map> tempMap) {
        List<IndexData> indexDataList = tempMap.stream()
                .map(this::getIndexData).collect(Collectors.toList());
        return indexDataList;
    }

    /**
     * map转成IndexData
     * @param map
     * @return
     */
    private IndexData getIndexData(Map map){
        String date = map.get("date").toString();
        float closePoint = Convert.toFloat(map.get("closePoint"));
        IndexData indexData = new IndexData();
        indexData.setDate(date);
        indexData.setClosePoint(closePoint);
        return indexData;
    }

    @Override
    @CacheEvict(key = "'code-' + #code")
    public void remove(String code) {

    }

    @Override
    @CachePut(key = "'code-' + #code")
    public List<IndexData> store(String code) {
        return indexDataMap.get(code);
    }

    @Override
    @Cacheable(key = "'code-' + #code")
    public List<IndexData> get(String code) {
        return new ArrayList<>();
    }

    /**
     * 断熔方法
     * @return
     */
    private List<IndexData> thirdPartNotConnected(){
        IndexData indexData = new IndexData();
        indexData.setClosePoint(0);
        indexData.setDate("n/a");
        return Collections.singletonList(indexData);
    }

}
