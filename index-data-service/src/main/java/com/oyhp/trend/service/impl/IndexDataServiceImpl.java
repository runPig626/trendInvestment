package com.oyhp.trend.service.impl;

import com.oyhp.trend.model.IndexData;
import com.oyhp.trend.service.IndexDataService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 指数数据service实现类
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-10
 */
@Service
@CacheConfig(cacheNames = "index_data")
public class IndexDataServiceImpl implements IndexDataService{

    @Override
    @Cacheable(key = "'code-' + #code")
    public List<IndexData> getIndexData(String code) {
        return new ArrayList<>();
    }

}
