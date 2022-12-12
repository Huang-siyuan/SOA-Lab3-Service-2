package com.example.soaspringcloudzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class SoaSpringCloudZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoaSpringCloudZuulApplication.class, args);
    }

}
