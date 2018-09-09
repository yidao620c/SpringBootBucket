## SpringBoot 全家桶

[![GitHub issues](https://img.shields.io/github/issues/yidao620c/SpringBootBucket.svg)](https://github.com/yidao620c/SpringBootBucket/issues)
[![License][licensesvg]][license]
[![Github downloads](https://img.shields.io/github/downloads/yidao620c/SpringBootBucket/total.svg)](https://github.com/yidao620c/SpringBootBucket/releases/latest)
[![GitHub release](https://img.shields.io/github/release/yidao620c/SpringBootBucket.svg)](https://github.com/yidao620c/SpringBootBucket/releases)

Spring Boot 现在已经成为Java 开发领域的一颗璀璨明珠，它本身是包容万象的，可以跟各种技术集成。

本项目对目前Web开发中常用的各个技术，通过和SpringBoot的集成，并且对各种技术通过“一篇博客 + 一个可运行项目”的形式来详细说明。

每个子项目都会使用最小依赖，大家拿来即可使用，自己可以根据业务需求自由组合搭配不同的技术构建项目。

**加粗提醒：**

1. master分支基于最新Spring Boot 2构建！
2. spring1.5分支基于Spring Boot 1.5.10构建！

## 项目简介

![](https://xnstatic-1253397658.file.myqcloud.com/SpringBootBucket.png)

## 子项目列表

每个子项目会配有一篇博客文章的详细讲解 :point_right:

项目名称                    | 文章地址
----------------------------|------------------------------------------------------------------------------------------
springboot-thymeleaf        | [集成Thymeleaf构建Web应用](https://www.xncoding.com/2017/07/01/spring/sb-thymeleaf.html)
springboot-mybatis          | [集成MyBatis](https://www.xncoding.com/2017/07/02/spring/sb-mybatis.html)
springboot-hibernate        | [集成Hibernate](https://www.xncoding.com/2017/07/03/spring/sb-hibernate.html)
springboot-mongodb          | [集成MongoDB](https://www.xncoding.com/2017/07/04/spring/sb-mongodb.html)
springboot-restful          | [实现RESTful接口](https://www.xncoding.com/2017/07/05/spring/sb-restful.html)
springboot-resttemplate     | [使用RestTemplate](https://www.xncoding.com/2017/07/06/spring/sb-restclient.html)
springboot-shiro            | [集成Shiro权限管理](https://www.xncoding.com/2017/07/07/spring/sb-shiro.html)
springboot-swagger2         | [集成Swagger2自动生成API文档](https://www.xncoding.com/2017/07/08/spring/sb-swagger2.html)
springboot-jwt              | [集成JWT实现接口权限认证](https://www.xncoding.com/2017/07/09/spring/sb-jwt.html)
springboot-multisource      | [多数据源配置](https://www.xncoding.com/2017/07/10/spring/sb-multisource.html)
springboot-schedule         | [定时任务](https://www.xncoding.com/2017/07/12/spring/sb-schedule.html)
springboot-cxf              | [cxf实现WebService](https://www.xncoding.com/2017/07/13/spring/sb-cxf.html)
springboot-websocket        | [使用WebScoket实时通信](https://www.xncoding.com/2017/07/15/spring/sb-websocket.html)
springboot-socketio         | [集成SocketIO实时通信](https://www.xncoding.com/2017/07/16/spring/sb-socketio.html)
springboot-async            | [异步线程池](https://www.xncoding.com/2017/07/20/spring/sb-async.html)
springboot-starter          | [教你自己写starter](https://www.xncoding.com/2017/07/22/spring/sb-starter.html)
springboot-aop              | [使用AOP](https://www.xncoding.com/2017/07/24/spring/sb-aop.html)
springboot-transaction      | [声明式事务](https://www.xncoding.com/2017/07/26/spring/sb-transaction.html)
springboot-cache            | [使用缓存](https://www.xncoding.com/2017/07/28/spring/sb-cache.html)
springboot-redis            | [Redis数据库](https://www.xncoding.com/2017/07/30/spring/sb-redis.html)
springboot-batch            | [批处理](https://www.xncoding.com/2017/08/01/spring/sb-batch.html)
springboot-rabbitmq         | [使用消息队列RabbitMQ](https://www.xncoding.com/2017/08/06/spring/sb-rabbitmq.html)
springboot-echarts          | [集成Echarts导出图片](https://www.xncoding.com/2017/08/19/spring/sb-echarts.html)

## 环境

* JDK 1.8
* Maven latest
* Spring Boot 2.0.4
* Intellij IDEA
* mysql 5.7
* mongodb
* git 版本管理
* nginx 反向代理
* redis 缓存
* rabbitmq 消息队列

## 运行

每个子项目都可以单独运行，都是打包成jar包后，通过使用内置jetty容器执行，有3种方式运行。:point_right:

1. 在IDEA里面直接运行Application.java的main函数。
2. 另一种方式是执行`mvn clean package`命令后传到linux服务器上面，通过命令`java -Xms64m -Xmx1024m -jar xxx.jar`方式运行
3. 在linux服务器上面，配置好jdk、maven、git命令后，通过`git clone sb-xxx`拉取工程后，执行`./run.sh start test`命令来执行

注：每个子项目有自己的README.md文件，告诉你该怎么初始化环境，比如准备好数据库SQL文件等。

另外，如果你需要打包成war包放到tomcat容器中运行，可修改pom.xml文件，将打包类型从jar改成war，打包后再放到容器中运行：

``` xml
<modelVersion>4.0.0</modelVersion>
<artifactId>springboot-cache</artifactId>
<packaging>war</packaging>
```

## 后续计划

1. 集成OAuth2认证
1. 集成GitHub、微信等第三方登录
1. 集成网络爬虫框架

## 问题反馈

1. 欢迎提issue一起完善这个项目
1. Email: yidao620@gmail.com
1. 个人主站: https://www.xncoding.com/

## 许可证

[![license](https://img.shields.io/badge/license-MIT-brightgreen.svg)](http://www.opensource.org/licenses/MIT)

Copyright (c) 2018 [Xiong Neng](https://www.xncoding.com/)

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>


[licensesvg]: https://img.shields.io/badge/license-MIT-brightgreen.svg
[license]: http://www.opensource.org/licenses/MIT

