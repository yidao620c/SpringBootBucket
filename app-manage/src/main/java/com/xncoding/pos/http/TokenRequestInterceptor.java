package com.xncoding.pos.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * TokenRequestInterceptor
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/23
 */
public class TokenRequestInterceptor implements ClientHttpRequestInterceptor {
    private String token;

    public TokenRequestInterceptor(String token) {
        this.token = token;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add("Authorization", token);
        return execution.execute(request, body);
    }
}
