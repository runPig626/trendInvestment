package com.oyhp.trend.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oyhp.trend.model.Index;
import com.oyhp.trend.service.IndexService;
import com.oyhp.trend.utils.SpringContextUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
 * 指数服务实现类
 * 用于收集第三方服务数据
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-08
 */
@Service
@CacheConfig(cacheNames = "indexes")
public class IndexServiceImpl implements IndexService{
    private List<Index> indexes;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public List<Index> fetchIndicesFromThirdPart(){
        String indexDataUrl = "http://127.0.0.1:8090/indexes/codes.json";
        List<Map> tempIndexes = restTemplate.getForObject(indexDataUrl,List.class);
        return map2Index(tempIndexes);
    }


    @Override
    public List<Index> thirdPartNotConnected(){
        Index index= new Index();
        index.setCode("000000");
        index.setName("无效指数代码");
        return Collections.singletonList(index);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void remove() {

    }

    @Override
    @Cacheable(key = "'all_codes'")
    public List<Index> getIndexes() {
        return new ArrayList<>();
    }

    @Override
    @Cacheable(key = "'all_codes'")
    public List<Index> store() {
        return indexes;
    }

    @Override
    @HystrixCommand(fallbackMethod = "thirdPartNotConnected")
    public List<Index> fresh() {
        indexes = this.fetchIndicesFromThirdPart();
        IndexService indexService = SpringContextUtil.getBean(IndexServiceImpl.class);
        indexService.remove();
        return indexService.store();
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
