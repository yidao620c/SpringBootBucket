package com.xncoding.trans.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * MyJobListener
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/3
 */
public class MyJobListener implements JobExecutionListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private long startTime;
    private long endTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        String jobName = jobExecution.getJobParameters().getString("input.job.name");
        logger.info("任务-{}处理开始", jobName);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        String jobName = jobExecution.getJobParameters().getString("input.job.name");
        logger.info("任务-{}处理结束，总耗时=" + (endTime - startTime) + "ms", jobName);
    }
}
