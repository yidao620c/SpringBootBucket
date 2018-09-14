package com.xncoding.webservice;

import com.xncoding.webservice.model.User;
import com.xncoding.webservice.service.ICommonService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTests {

    private Integer port;
    /**
     * 接口地址
     */
    private String wsdlAddress;

    @Before
    public void prepare() {
        wsdlAddress = "http://localhost:8092/services/CommonService?wsdl";
    }

    /**
     * 方式1.代理类工厂的方式,需要拿到对方的接口
     */
    @Test
    public void cl1() {
        try {
            // 接口地址
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(wsdlAddress);
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(ICommonService.class);
            // 创建一个代理接口实现
            ICommonService cs = (ICommonService) jaxWsProxyFactoryBean.create();
            // 数据准备
            String userName = "Leftso";
            // 调用代理接口的方法调用并返回结果
            String result = cs.sayHello(userName);
            System.out.println("返回结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方式2. 动态调用方式
     */
    @Test
    public void cl2() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdlAddress);
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("sayHello", "Leftso");
            System.out.println("返回类型：" + objects[0].getClass());
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方式3. 动态调用方式，返回对象User
     */
    @Test
    public void cl3() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdlAddress);
        Object[] objects;
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getUser", "张三");
            System.out.println("返回类型：" + objects[0].getClass());
            System.out.println("返回数据:" + objects[0]);
            User user = (User) objects[0];
            System.out.println("返回对象User.name=" + user.getName());
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    // /**
    //  * 方式4. 客户端代码生成方式
    //  */
    // @Test
    // public void cl4() {
    //     CommonService_Service c = new CommonService_Service();
    //     com.xncoding.webservice.client.User user = c.getCommonServiceImplPort().getUser("Tom");
    //     assertThat(user.getName(), is("Tom"));
    // }

}
