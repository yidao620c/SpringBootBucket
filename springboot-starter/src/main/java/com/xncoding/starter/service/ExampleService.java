package com.xncoding.starter.service;

/**
 * ExampleService
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/28
 */
public class ExampleService {

    private String prefix;
    private String suffix;

    public ExampleService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }
    public String wrap(String word) {
        return prefix + word + suffix;
    }
}
