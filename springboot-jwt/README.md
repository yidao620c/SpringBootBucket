
## 简介

一般来讲，对于RESTful API都会有认证(Authentication)和授权(Authorization)过程，保证API的安全性。

采用TOKEN认证，这种方式也是再HTTP头中，使用Authorization: Bearer <token>，使用最广泛的TOKEN是JWT，通过签名过的TOKEN。

基于Shiro+JWT可实现Token认证方式

## 测试

启动应用后，先访问登录接口，使用参数用户名=admin/密码=12345678，拿到token后再访问其他接口。

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>


