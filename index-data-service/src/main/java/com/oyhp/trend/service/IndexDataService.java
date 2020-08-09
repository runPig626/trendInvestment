package com.oyhp.trend.service;

import com.oyhp.trend.model.IndexData;

import java.util.List;

/**
 * 指数数据service接口
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-10
 */
public interface IndexDataService {

    /**
     * 根据指数代码code，获取指数数据indexData
     * @param code
     * @return
     */
    List<IndexData> getIndexData(String code);

}
