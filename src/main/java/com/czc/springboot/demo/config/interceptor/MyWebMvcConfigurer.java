package com.czc.springboot.demo.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {
public class MyWebMvcConfigurer implements WebMvcConfigurer {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            //注册拦截器 拦截规则
            //多个拦截器时 以此添加 执行顺序按添加顺序
            // 注意 :拦截器的 /* 只拦截一级路径,/** 才是拦截所有路径 !!!!
            registry.addInterceptor(getHandlerInterceptor()).addPathPatterns("/**");
        }

        @Bean
        public static HandlerInterceptor getHandlerInterceptor() {
            return new CustomHandlerInterceptor();
        }
}