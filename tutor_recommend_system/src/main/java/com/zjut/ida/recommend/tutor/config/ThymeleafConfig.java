package com.zjut.ida.recommend.tutor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

/**
 * @author wly
 * @date 2021/5/3 13:46
 */
@Configuration
public class ThymeleafConfig {

    @Bean
    public IDialect conditionalCommentDialect() {
        return new Java8TimeDialect();
    }
}
