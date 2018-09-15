# Table of Contents

  * [简介](#简介)
  * [测试](#测试)
  * [许可证](#许可证)


## 简介

一般来讲，对于RESTful API都会有认证(Authentication)和授权(Authorization)过程，保证API的安全性。

采用TOKEN认证，这种方式也是再HTTP头中，使用`Authorization: Bearer <token>`，使用最广泛的TOKEN是JWT，通过签名过的TOKEN。

基于Shiro+JWT可实现Token认证方式

## 测试

启动应用后

1. 先访问登录接口/login

*URL*

```
POST http://localhost:9095/login
```

*Header参数*

```
Content-Type: application/json
```

*Body参数*

``` json
{
	"username": "admin",
	"password": "12345678"
}
```

可使用postman或者curl方式，本人更愿意使用curl方式：

```
 curl -X POST http://localhost:9095/login -H 'Content-Type: application/json' -d '
{
    "username": "admin",
    "password": "12345678"
}
'
```

返回值：

``` json
{
    "success": true,
    "msg": "Login success",
    "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhcHBpZCI6IjExMSIsImltZWkiOiJpbWVpIiwiZXhwIjoxNTM2NDg3NTM1LCJ1c2VybmFtZSI6ImFkbWluIn0.uat7rvVLwC7bcM-jRs329RWdHIFC6P-YN7YdJrdRUHE"
}
```

2. 使用token再去访问接口

上面的"data"对应的一长串字符串就是返回的token值

*URL*

```
GET http://localhost:9095/api/v1/join?imei=imei
```

*Header参数*

```
Content-Type: application/json
Authorization: "上面拿到的token值"
```

curl访问语法：

```
curl -X GET http://localhost:9095/api/v1/join?imei=imei -H 'Content-Type: application/json' -H 'Authorization: 上面拿到的token值'
```

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>


