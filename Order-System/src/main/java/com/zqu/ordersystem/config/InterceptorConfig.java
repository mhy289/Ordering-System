package com.zqu.ordersystem.config;

import com.zqu.ordersystem.interceptor.AuthInterceptor;
import com.zqu.ordersystem.interceptor.CountExecTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CountExecTimeInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
