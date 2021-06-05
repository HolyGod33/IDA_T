package com.zjut.ida.recommend.tutor.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private RecommendInterceptor recommendInterceptor;
    @Autowired
    private UniversalInterceptor universalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(recommendInterceptor)
                .addPathPatterns("/recommend/**");
        registry.addInterceptor(universalInterceptor)
                .addPathPatterns("/student/**", "/favorite/**", "/tutor/**", "/cf/**");
//                .excludePathPatterns("/static/**");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//    }
}
