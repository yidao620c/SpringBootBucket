## 实现WebService

利用Apache CXF实现WebService

启动之后，wsdl访问链接：<http://localhost:8092/services/CommonService?wsdl>

## 客户端动态代理调用

这个在单元测试类ApplicationTests中有演示，这里要注意的是model类的包名一定要放到指定的路径下。
也就是targetNamespace的倒叙包名中。

## 客户端代码生成

有两种方式生成客户端调用代码

**Apache的wsdl2java工具**

```
wsdl2java -autoNameResolution http://xxx?wsdl
```

**JDK自带的工具（推荐）**

```
wsimport -encoding utf-8 -p com.xncoding.webservice.client -keep http://xxx?wsdl -s d:/ws -B-XautoNameResolution
```

其中：

```
-encoding ：指定编码格式（此处是utf-8的指定格式）
-keep：是否生成Java源文件
-s：指定.java文件的输出目录
-d：指定.class文件的输出目录
-p：定义生成类的包名，不定义的话有默认包名
-verbose：在控制台显示输出信息
-b：指定jaxws/jaxb绑定文件或额外的schemas
-extension：使用扩展来支持SOAP1.2
```

生成的客户端代码不能改包名

``` java
CommonService_Service c = new CommonService_Service();
com.xncoding.webservice.client.User user = c.getCommonServiceImplPort().getUser("Tom");
assertThat(user.getName(), is("Tom"));
```

## 测试步骤

先启动springboot项目，然后执行`com.xncoding.webservice.ApplicationTests`单元测试类。

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
