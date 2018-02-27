package com.xncoding.trans.start;

import com.xncoding.trans.service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 内网服务启动器
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/27
 */
@Component
public class StartRunner implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CsvService csvService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("导入数据主进程启动啦啦啦...");
        csvService.importTables();
        logger.info("导入数据主进程完成啦啦啦...");
    }
}
