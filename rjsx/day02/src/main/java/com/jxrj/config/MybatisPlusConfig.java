package com.jxrj.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {


    @Bean
    public MybatisPlusInterceptor pageInterceptor(){

        // 分页拦截器
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();

        // 存放拦截器的容器
        MybatisPlusInterceptor interceptors = new MybatisPlusInterceptor();

        interceptors.addInnerInterceptor(innerInterceptor);

        return interceptors;
    }

}
