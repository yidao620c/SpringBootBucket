## 集成MongoDB

SpringBoot集成MongoDB访问NoSQL数据库

## 安装MongoDB数据库

数据库的安装教程网上非常多，我在CentOS7上面安装，版本是4.0
参考了 [官网安装](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat/)

安装完成后，配置数据库的账号和密码：

```
mongo --port 27017
use test
db.createUser(
   {
     user: "xiongneng",
     pwd: "123456",
     roles: [ { role: "readWrite", db: "test" } ]
   }
 )
```

## 修改application.yml

修改配置文件，主要是MongoDB的连接信息

## 运行测试用例

执行对用户表增/删/改/查的测试用例：`com.xncoding.pos.ApplicationTests.java`

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
