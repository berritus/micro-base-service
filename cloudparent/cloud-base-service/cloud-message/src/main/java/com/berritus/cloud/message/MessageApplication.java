package com.berritus.cloud.message;

import com.berritus.mis.core.cache.annotation.EnableMisCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"com.berritus.cloud.message.dao"})
@ComponentScan({"com.berritus.cloud"})
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
@EnableScheduling
@EnableMisCache
@EnableFeignClients(basePackages = {"com.berritus.cloud.message.feign.client"})
public class MessageApplication {
    public static void main(String[] args){
        SpringApplication.run(MessageApplication.class, args);
    }
}
