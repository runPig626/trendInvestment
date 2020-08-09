package com.oyhp.trend.service;

import com.oyhp.trend.model.IndexData;

import java.util.List;

/**
 * 指数数据接口类
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
public interface IndexDataService {
    /**
     * 刷新redis缓存指数数据
     * @return
     */
    List<IndexData> fresh(String code);

    /**
     * 删除redis缓存指数代码数据
     * @param code
     */
    void remove(String code);

    /**
     * 存储redis缓存指数代码数据
     * @return
     * @param code
     */
    List<IndexData> store(String code);

    /**
     * 获取redis缓存指数代码数据
     * @param code 指数代码
     * @return
     */
    List<IndexData> get(String code);

}
