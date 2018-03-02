## 集成Hibernate

SpringBoot集成Hibernate做DAO层

## 安装MySQL数据库

数据库的安装教程网上非常多，版本最好是mysql5.5+

配置数据库的账号和密码。

## 修改application.yml

修改配置文件，主要是mysql的账号和密码

## 数据库初始化

创建数据库pos，然后执行SQL文件`src/main/resources/sql/schema.sql`，创建表

## 运行测试用例

执行对用户表增/删/改/查的测试用例：`com.xncoding.pos.ApplicationTests.java`


## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
