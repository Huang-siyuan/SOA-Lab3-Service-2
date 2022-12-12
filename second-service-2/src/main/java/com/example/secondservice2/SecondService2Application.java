package com.example.secondservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
//@RibbonClient(name = "SOA-SECOND-SERVICE-2", configuration = LoadBalanceRule.class)
public class SecondService2Application {

	public static void main(String[] args) {
		SpringApplication.run(SecondService2Application.class, args);
	}

}
