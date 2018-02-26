## SpringBoot Batch演示项目

SpringBoot对批处理的支持演示

## 初始化

批处理初始化采用手动模式最好，先执行/resources/sql/下面对应SQL语句的初始化，
任务完成后再执行清除语句。

## 运行

采用手动触发方式，对于每种类型的任务，只需要在/modules/common/vo/下面定义对应的Bean类即可。

然后参照测试类中方法：

``` java
@Test
public void testRunSimple() throws Exception {
    runTask(BscCanton.class);
    runTask(BscOfficeExeItem.class);
    runTask(BscExeOffice.class);
    runTask(BscTollItem.class);
    while (true) {
        Thread.sleep(200000L);
    }
}
```

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>

