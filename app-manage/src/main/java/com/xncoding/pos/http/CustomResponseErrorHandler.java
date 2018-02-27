package com.xncoding.pos.http;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * RestTemplate自定义异常处理
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/24
 */
public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        super.handleError(response);
    }
}
