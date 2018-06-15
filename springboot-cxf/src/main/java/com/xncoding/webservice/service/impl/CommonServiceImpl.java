package com.xncoding.webservice.service.impl;

import com.xncoding.webservice.model.User;
import com.xncoding.webservice.service.ICommonService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * CommonServiceImpl
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/6/15
 */
@WebService(serviceName = "CommonService", // 与接口中指定的name一致
        targetNamespace = "http://model.webservice.xncoding.com/", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.xncoding.webservice.service.ICommonService"// 接口地址
)
@Component
public class CommonServiceImpl implements ICommonService {

    @Override
    public String sayHello(String name) {
        return "Hello ," + name;
    }

    @Override
    public User getUser(String name) {
        return new User(1000L, name, 23);
    }
}
