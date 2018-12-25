package com.cskaoyan.demo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: YangTao
 * @Date: 2018/12/24 0024
 * 产生一个restTemplate 一个实例
 */
@Configuration
public class RestConfiguration {
    @Autowired
    private RestTemplateBuilder builder;
    @Bean
    RestTemplate restTemplate(){return  builder.build();}


}
