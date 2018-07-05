package com.cdzksh.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
@SpringBootConfiguration
public class IndexConfig implements WebMvcConfigurer {
    @Autowired
    IndexInterceptor indexInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexInterceptor).addPathPatterns("/api/manage/**");
    }
}
