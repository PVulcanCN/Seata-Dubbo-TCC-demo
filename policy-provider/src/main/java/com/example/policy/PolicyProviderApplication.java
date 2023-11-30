package com.example.policy;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.example.policy.mapper")
@EnableDubbo
@EnableDiscoveryClient
public class PolicyProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolicyProviderApplication.class, args);
    }

}
