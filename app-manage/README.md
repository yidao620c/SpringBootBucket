## POS机具管理
对于行政收费、螺蛳湾收费项目、电力收费项目、烟草收费项目，以及将来任意一个收费项目，
统一对POS机机具进行管理。实现POS机收费APP的入网、自动更新、在线监控等。

* POS机具入网
* 在线监控，故障报修
* APP版本发布管理
* 用户管理

## 技术选型

采用SpringBoot框架搭建，请参考 [官方文档指南](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

* MVC - SpringMVC
* 页面模板 - 使用 Thymeleaf 3
* 权限管理 - 使用Shiro做角色权限控制
* DAO层 - 使用Mybatis-plus进行数据库访问
* 缓存层 - 内存缓存使用Ehcache，分布式缓存使用Redis
* 监控 - 除了SpringBoot自带的监控外，还使用了Druid监控插件

## 上线发布流程

项目如果要发布到开发/测试/演示/生产环境，步骤如下：

1. 修改对应环境配置文件`application.yml`后push到git服务器
2. 运行脚本 `./run.sh start {profile}`

## 高可用建议

建议三节点虚拟机部署或者docker容器集群，如果是自己内部使用可不需要高可用。

1. 反向代理高可用：2个nginx做标准的负载均衡策略
1. web应用高可用：每个nginx => 2个应用实例
1. 数据库高可用：keepalived+mysql双主高可用

## 代码生成

MyBatis DAO层相关代码生成方法：

修改`src/main/resources/sql/generate_mapper.py`中的参数后运行即可

## 数据库初始化

执行SQL文件`src/main/resources/sql/schema.sql`

## 加入依赖包

里面有个依赖是我本地依赖，需要你先手动安装，路径`src/main/resources/libs/AXMLPrinter-1.0.jar`

本地安装maven包的命令：

```
mvn install:install-file -Dfile=AXMLPrinter-1.0.jar -DgroupId=com.xncoding -DartifactId=AXMLPrinter -Dversion=1.0 -Dpackaging=jar
```

## 测试账号

1. admin/12345678
2. test/12345678

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
