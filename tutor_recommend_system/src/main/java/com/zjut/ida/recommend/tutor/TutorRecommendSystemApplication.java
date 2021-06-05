package com.zjut.ida.recommend.tutor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@EnableConfigurationProperties
@MapperScan("com.zjut.ida.recommend.tutor.dao")
@EnableTransactionManagement
@SpringBootApplication
public class TutorRecommendSystemApplication {
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static void main(String[] args) {
        SpringApplication.run(TutorRecommendSystemApplication.class, args);
    }

}
