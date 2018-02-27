## 定时任务

定时任务非常简单，只需要写个配置类，然后定义定时任务类，使用注解定义某个方法定期执行

``` java
@Scheduled(cron = "0 26 19 * * ?")
public void checkState1() {
    logger.info(">>>>> xxx检查开始....");
    logger.info(">>>>> xxx传检查完成....");
}
```

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
