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

    /**
     * 第三方服务未启动时，断熔方法
     * @return 无效Index
     */
    List<Index> thirdPartNotConnected();

    /**
     * 清空redis缓存数据
     */
    void remove();

    /**
     * 获取redis缓存数据
     * @return
     */
    List<Index> getIndexes();

    /**
     * 保存redis缓存数据
     * @return
     */
    List<Index> store();

    /**
     * 刷新数据
     * 1、先运行fetchIndicesFromThirdPart来获取数据
     * 2、删除数据
     * 3、保存数据
     * @return
     */
    List<Index> fresh();
}
