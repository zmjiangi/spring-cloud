package com.yijian.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zmjiangi on 2018/5/28.
 */

@EnableHystrix
@EnableEurekaClient
@EnableDiscoveryClient // Eureka Discovery Client 标识
@SpringBootApplication // Spring Boot 应用标识
@EnableHystrixDashboard
@EnableFeignClients(basePackages={"com.yijian.api.service"})
@ComponentScan(basePackages={"com.yijian.customer", "com.yijian.api"})
public class CustomerApplication {


    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(CustomerApplication.class, args);
    }

}
