package com.walnut.core.frame.spring.interceptor;

import com.walnut.core.api.logic.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class InterceptorConfigurer implements WebMvcConfigurer {
    @Autowired
    TokenService tokenService;

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(tokenService)).addPathPatterns("/api/**");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new TimerInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new LockInterceptor()).addPathPatterns("/api/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
