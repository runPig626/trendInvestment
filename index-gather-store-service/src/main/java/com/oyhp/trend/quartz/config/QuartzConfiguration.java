package com.oyhp.trend.quartz.config;

import com.oyhp.trend.quartz.job.IndexDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz定时器配置 定时器，触发器，任务
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
@Configuration
public class QuartzConfiguration {

    private static final int INTERVAL = 1;

    /**
     * 定时任务
     * @return
     */
    @Bean
    public JobDetail weatherDataSyncJobDetail(){
        return JobBuilder.newJob(IndexDataSyncJob.class).withIdentity("indexDataSyncJob")
                .storeDurably().build();
    }

    /**
     * 触发器
     * @return
     */
    @Bean
    public Trigger weatherDataSyncTrigger(){
        // 调度器
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(INTERVAL).repeatForever();
        // 触发器
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
                .withIdentity("indexDataSyncTrigger").withSchedule(scheduleBuilder).build();
    }

}
