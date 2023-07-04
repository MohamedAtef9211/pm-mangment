package com.base.pm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppCommonConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofMillis(30000))
                .setConnectTimeout(Duration.ofMillis(10000))
                .build();
    }

    @Bean
    public AntPathMatcher antPathMatcher() {return new AntPathMatcher();}

}
