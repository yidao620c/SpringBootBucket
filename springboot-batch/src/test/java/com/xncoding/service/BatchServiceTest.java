package com.xncoding.service;

import com.xncoding.trans.Application;
import com.xncoding.trans.config.properties.CommonProperties;
import com.xncoding.trans.modules.common.anno.TableName;
import com.xncoding.trans.modules.common.vo.*;
import com.xncoding.trans.service.CsvService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * BatchServiceTest
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BatchServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommonProperties p;
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("commonJob")
    private Job commonJob;

    @Autowired
    @Qualifier("vtollJob")
    private Job vtollJob;

    @Autowired
    @Qualifier("cantonJob")
    private Job cantonJob;

    @Autowired
    @Qualifier("zappJob")
    private Job zappJob;

    @Autowired
    @Qualifier("zlogJob")
    private Job zlogJob;

    @Resource
    private CsvService csvService;

    private static final String KEY_JOB_NAME = "input.job.name";
    private static final String KEY_FILE_NAME = "input.file.name";
    private static final String KEY_VO_NAME = "input.vo.name";
    private static final String KEY_COLUMNS = "input.columns";
    private static final String KEY_SQL = "input.sql";

    @Test
    public void testBudgetVtoll() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.name", p.getCsvVtoll())
                .toJobParameters();
        jobLauncher.run(vtollJob, jobParameters);
        logger.info("Main线程执行完成");
        while (true) {
            Thread.sleep(2000000L);
        }
    }
    @Test
    public void testCanton() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.name", p.getCsvCanton())
                .toJobParameters();
        jobLauncher.run(cantonJob, jobParameters);
        logger.info("Main线程执行完成");

        while (true) {
            Thread.sleep(2000000L);
        }
    }

    /**
     * 测试一个配置类，可同时运行多个任务
     * @throws Exception 异常
     */
    @Test
    public void testCommonJobs() throws Exception {
        JobParameters jobParameters1 = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .addString(KEY_JOB_NAME, "App")
                .addString(KEY_FILE_NAME, p.getCsvApp())
                .addString(KEY_VO_NAME, "com.xncoding.trans.modules.zapp.App")
                .addString(KEY_COLUMNS, String.join(",", new String[]{
                        "appid", "zname", "flag"
                }))
                .addString(KEY_SQL, "insert into z_test_App (appid, zname, flag) values(:appid, :zname, :flag)")
                .toJobParameters();
        jobLauncher.run(commonJob, jobParameters1);

        JobParameters jobParameters2 = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .addString(KEY_JOB_NAME, "Log")
                .addString(KEY_FILE_NAME, p.getCsvLog())
                .addString(KEY_VO_NAME, "com.xncoding.trans.modules.zlog.Log")
                .addString(KEY_COLUMNS, String.join(",", new String[]{
                        "logid", "msg", "logtime"
                }))
                .addString(KEY_SQL, "insert into z_test_Log (logid, msg, logtime) values(:logid, :msg, :logtime)")
                .toJobParameters();
        jobLauncher.run(commonJob, jobParameters2);

        logger.info("Main线程执行完成");

        while (true) {
            Thread.sleep(2000000L);
        }
    }

    /**
     * 一起测试4个CSV文件导入
     * @throws Exception 异常
     */
    @Test
    public void testImportCsv4() throws Exception {
        JobParameters jobParameters1 = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .addString(KEY_JOB_NAME, "BscExeOffice")
                .addString(KEY_FILE_NAME, p.getCsvExeOffice())
                .addString(KEY_VO_NAME, "com.xncoding.trans.modules.common.vo.BscExeOffice")
                .addString(KEY_COLUMNS, String.join(",", new String[]{
                        "id","cantonid","code","name","memcode","supdeptid","comdeptid","contactman","tel","mobil","email","bgofficeid","infomobil","infoman","logpass","startdate","stopdate","status","memo","auditer","audittime","isaudit","edittime","platform_id","isprintbill"
                }))
                .addString(KEY_SQL, "insert into NT_BSC_EXEOFFICE (F_ID,F_CANTONID,F_CODE,F_NAME,F_MEMCODE,F_SUPDEPTID,F_COMDEPTID,F_CONTACTMAN,F_TEL,F_MOBIL,F_EMAIL,F_BGOFFICEID,F_INFOMOBIL,F_INFOMAN,F_LOGPASS,F_STARTDATE,F_STOPDATE,F_STATUS,F_MEMO,F_AUDITER,F_AUDITTIME,F_ISAUDIT,F_EDITTIME,F_PLATFORM_ID,F_ISPRINTBILL)" +
                        " values(:id, :cantonid, :code, :name, :memcode, :supdeptid, :comdeptid, :contactman, :tel, :mobil, :email, :bgofficeid, :infomobil, :infoman, :logpass, :startdate, :stopdate, :status, :memo, :auditer, :audittime, :isaudit, :edittime, :platform_id, :isprintbill)")
                .toJobParameters();
        jobLauncher.run(commonJob, jobParameters1);

//        JobParameters jobParameters2 = new JobParametersBuilder()
//                .addLong("time",System.currentTimeMillis())
//                .addString(KEY_JOB_NAME, "Log")
//                .addString(KEY_FILE_NAME, p.getCsvLog())
//                .addString(KEY_VO_NAME, "com.xncoding.trans.modules.zlog.Log")
//                .addString(KEY_COLUMNS, String.join(",", new String[]{
//                        "logid", "msg", "logtime"
//                }))
//                .addString(KEY_SQL, "insert into z_test_Log (logid, msg, logtime) values(:logid, :msg, :logtime)")
//                .toJobParameters();
//        jobLauncher.run(commonJob, jobParameters2);

        logger.info("Main线程执行完成");

        while (true) {
            Thread.sleep(2000000L);
        }
    }

    /**
        CREATE TABLE Z_TEST_APP (
            appid INT,
            zname VARCHAR2 (20),
            flag VARCHAR2 (2),
            CONSTRAINT app_pk PRIMARY KEY (appid)
         );

         CREATE TABLE Z_TEST_LOG (
            logid INT,
            msg VARCHAR2 (20),
            logtime VARCHAR2 (8),
            CONSTRAINT log_pk PRIMARY KEY (logid)
         );
     * @throws Exception
     */
    @Test
    public void testTwoJobs() throws Exception {
        JobParameters jobParameters1 = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.name", p.getCsvApp())
                .toJobParameters();
        jobLauncher.run(zappJob, jobParameters1);

        JobParameters jobParameters2 = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.name", p.getCsvLog())
                .toJobParameters();
        jobLauncher.run(zlogJob, jobParameters2);

        logger.info("Main线程执行完成");
        while (true) {
            Thread.sleep(2000000L);
        }
    }

    @Test
    public void testRunSimple() throws Exception {
        csvService.runTask(BscCanton.class);
        csvService.runTask(BscOfficeExeItem.class);
        csvService.runTask(BscExeOffice.class);
        csvService.runTask(BscTollItem.class);
        while (true) {
            Thread.sleep(200000L);
        }
    }
}
