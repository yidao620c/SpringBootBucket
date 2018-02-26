## SpringBoot AOP演示项目

面向切面编程

几个重要概念搞清楚就行

* 执行点（Executepoint） - 类初始化，方法调用。
* 连接点（Joinpoint）    - 执行点+方位的组合，可确定Joinpoint，比如类开始初始化前，类初始化后，方法调用前，方法调用后。
* 切点（Pointcut）       - 在众多执行点中，定位感兴趣的执行点。Executepoint相当于数据库表中的记录，而Pointcut相当于查询条件。
* 增强（Advice）         - 织入到目标类连接点上的一段程序代码。除了一段程序代码外，还拥有执行点的方位信息。
* 目标对象（Target）     - 增强逻辑的织入目标类
* 引介（Introduction）   - 一种特殊的增强（advice），它为类添加一些额外的属性和方法，动态为业务类添加其他接口的实现逻辑，让业务类成为这个接口的实现类。
* 代理（Proxy）          - 一个类被AOP织入后，产生一个结果类，它便是融合了原类和增强逻辑的代理类。
* 切面（Aspect）         - 切面由切点（Pointcut）和增强（Advice/Introduction）组成，既包括横切逻辑定义，也包括连接点定义。

AOP工作重点：

1. 如何通过切点（Pointcut）和增强（Advice）定位到连接点（Jointpoint）上；
2. 如何在增强（Advice）中编写切面的代码。

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
