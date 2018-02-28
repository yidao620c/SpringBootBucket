## 配置多数据源

SpringBoot配置多数据源，使用MyBatis作为DAO层演示

## 安装MySQL数据库

数据库的安装教程网上非常多，版本最好是mysql5.5+，配置数据库的账号和密码。
并且创建两个数据库pos和biz来测试多数据源。

## 修改application.yml

修改配置文件，主要是mysql的账号和密码，两个数据库都要配置

## 数据库初始化

然后执行SQL文件`src/main/resources/sql/schema.sql`

```
# 下面是核心数据库中的插入数据
INSERT INTO `t_user` VALUES (1,'admin','系统管理员','123456','www', '17890908889', '系统管理员', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_user` VALUES (2,'aix','张三','123456','eee', '17859569358', '', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');

# 下面是biz数据库中的插入数据
INSERT INTO `t_user` VALUES (1,'admin1','系统管理员','123456','www', '17890908889', '系统管理员', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_user` VALUES (2,'aix1','张三','123456','eee', '17859569358', '', 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');

```

## 运行测试用例

执行对用户表增/删/改/查的测试用例：`com.xncoding.pos.ApplicationTests.java`

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
