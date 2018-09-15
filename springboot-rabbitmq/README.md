## 集成消息队列RabbitMQ

消息队列RabbitMQ的使用例子，演示Direct模式和广播发送模式。

## 测试步骤

1. 先安装rabbitmq并初始化spring用户
2. 配置application.yml里面的连接信息
3. 启动程序

有两种测试方式，一种是通过编写AfterStartRunner来直接看启动效果。
另外一种是运行测试用例：`com.xncoding.service.SenderServiceTest.java`

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
