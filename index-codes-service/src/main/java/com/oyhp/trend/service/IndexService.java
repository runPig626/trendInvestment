package com.oyhp.trend.service;

import com.oyhp.trend.model.Index;

import java.util.List;

/**
 * 指数代码Service接口
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
public interface IndexService {

    /**
     * 获取指数代码
     * @return
     */
    List<Index> getIndexes();
}
