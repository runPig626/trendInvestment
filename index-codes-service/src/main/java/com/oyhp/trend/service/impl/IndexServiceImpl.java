package com.oyhp.trend.service.impl;

import com.oyhp.trend.model.Index;
import com.oyhp.trend.service.IndexService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 指数代码Service实现类
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
@Service
@CacheConfig(cacheNames = "indexes")
public class IndexServiceImpl implements IndexService {

    @Override
    @Cacheable(key = "'all_codes'")
    public List<Index> getIndexes() {
        Index index = new Index();
        index.setName("无效指数代码");
        index.setCode("000000");
        return Collections.singletonList(index);
    }

}
