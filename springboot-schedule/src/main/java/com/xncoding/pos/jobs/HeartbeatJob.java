package com.xncoding.pos.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * 第一位，表示秒，取值0-59
 * 第二位，表示分，取值0-59
 * 第三位，表示小时，取值0-23
 * 第四位，日期天/日，取值1-31
 * 第五位，日期月份，取值1-12
 * 第六位，星期，取值1-7，1表示星期天，2表示星期一
 * 第七位，年份，可以留空，取值1970-2099
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2017/9/27
 */
@Component
public class HeartbeatJob {
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatJob.class);

    /**
     * 检查状态1
     */
    @Scheduled(cron = "0 30 12 * * ?")
    public void checkState1() {
        logger.info(">>>>> cron中午12:30上传检查开始....");
        logger.info(">>>>> cron中午12:30上传检查完成....");
    }

    /**
     * 检查状态2
     */
    @Scheduled(cron = "0 0 18 * * ?")
    public void checkState2() {
        logger.info(">>>>> cron晚上18:00上传检查开始....");
        logger.info(">>>>> cron晚上18:00上传检查完成....");
    }


}
