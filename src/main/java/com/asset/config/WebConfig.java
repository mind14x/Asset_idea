package com.asset.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 针对/api路径下的请求启用跨域
                .allowedOrigins("*")  // 允许所有来源
                .allowedMethods("*")  // 允许所有HTTP方法
                .allowedHeaders("*"); // 允许所有HTTP头
    }
}
