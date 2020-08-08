package com.oyhp.trend.service;

import com.oyhp.trend.model.Index;

import java.util.List;

/**
 * 指数服务类
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-08
 */
public interface IndexService {

    /**
     * 从第三方服务获取到指数数据
     * 然后转成Index指数Model
     * @return 指数数据
     */
    List<Index> fetchIndicesFromThirdPart();
}
