package com.oyhp.trend.quartz.job;

import cn.hutool.core.date.DateUtil;
import com.oyhp.trend.model.Index;
import com.oyhp.trend.service.IndexDataService;
import com.oyhp.trend.service.IndexService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * 指数数据定时刷新任务
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
public class IndexDataSyncJob extends QuartzJobBean{
    /** Logger */
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IndexService indexService;

    @Resource
    private IndexDataService indexDataService;

    /**
     * 定时刷新redis缓存数据
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("定时启动：" + DateUtil.now());
        List<Index> indexes = indexService.fresh();
        for (Index index : indexes) {
            indexDataService.fresh(index.getCode());
        }
        LOG.info("定时结束：" + DateUtil.now());
    }
}
