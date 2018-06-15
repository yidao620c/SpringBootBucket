## 实现WebService

利用Apache CXF实现WebService

启动之后，wsdl访问链接：<http://localhost:8092/services/CommonService?wsdl>

## 客户端动态代理调用

这个在单元测试类ApplicationTests中有演示

## 生产客户端代码

apache的wsdl2java工具，使用`-autoNameResolution`自动处理

```
wsdl2java -autoNameResolution http://xxx?wsdl
```

JDK自带的工具

```
wsimport -p com.enzhico.land.client -keep http://xxx?wsdl -s d:/ws -B-XautoNameResolution
```

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
