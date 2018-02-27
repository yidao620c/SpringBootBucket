package com.xncoding.trans.service;

import com.xncoding.trans.config.properties.CommonProperties;
import com.xncoding.trans.modules.common.anno.TableName;
import com.xncoding.trans.modules.common.vo.BscCanton;
import com.xncoding.trans.modules.common.vo.BscExeOffice;
import com.xncoding.trans.modules.common.vo.BscOfficeExeItem;
import com.xncoding.trans.modules.common.vo.BscTollItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CommonProperties p;
    @Resource
    private JobLauncher jobLauncher;
    @Resource
    @Qualifier("commonJob")
    private Job commonJob;
    private static final String KEY_JOB_NAME = "input.job.name";
    private static final String KEY_FILE_NAME = "input.file.name";
    private static final String KEY_VO_NAME = "input.vo.name";
    private static final String KEY_COLUMNS = "input.columns";
    private static final String KEY_SQL = "input.sql";

    /**
     * 导入数据库数据
     * @throws Exception ex
     */
    public void importTables() throws Exception {
        runTask(BscCanton.class);
        runTask(BscOfficeExeItem.class);
        runTask(BscExeOffice.class);
        runTask(BscTollItem.class);
    }

    /**
     * 根据类名反射运行相应的任务
     *
     * @param c 定义的Bean类
     */
    public void runTask(Class c) throws Exception {
        TableName a = (TableName) c.getAnnotation(TableName.class);
        String tableName = a.value();
        Field[] fields = c.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        List<String> paramNames = new ArrayList<>();
        for (Field f : fields) {
            fieldNames.add(f.getName());
            paramNames.add(":" + f.getName());
        }
        String columnsStr = String.join(",", fieldNames);
        String paramsStr = String.join(",", paramNames);
        String csvFileName;
        if (p.getLocation() == 1) {
            csvFileName = p.getCsvDir() + tableName + ".csv";
        } else {
            csvFileName = tableName + ".csv";
        }
        JobParameters jobParameters1 = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString(KEY_JOB_NAME, tableName)
                .addString(KEY_FILE_NAME, csvFileName)
                .addString(KEY_VO_NAME, c.getCanonicalName())
                .addString(KEY_COLUMNS, String.join(",", fieldNames))
                .addString(KEY_SQL, "insert into " + tableName + " (" + columnsStr + ")" + " values(" + paramsStr + ")")
                .toJobParameters();
        jobLauncher.run(commonJob, jobParameters1);
    }

}
