package com.beizhi.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by eric on 16/11/7.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceA_Application {
    public static void main(String[] args) {
        SpringApplication.run(ServiceA_Application.class, args);
    }
}