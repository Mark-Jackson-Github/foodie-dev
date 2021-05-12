package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig(){

    }

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config=new CorsConfiguration();
        //建议指定，而不是用*，这样比较安全
        config.addAllowedOrigin("http://localhost:8083");
        //设置是否发送cookie信息
        config.setAllowCredentials(true);
        //设置允许所有请求方式
        config.addAllowedMethod("*");
        //设置允许所有header
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource corsSource=new UrlBasedCorsConfigurationSource();
        //指定对哪些接口配置跨域访问
        corsSource.registerCorsConfiguration("/**",config);
        return new CorsFilter(corsSource);
    }

}
