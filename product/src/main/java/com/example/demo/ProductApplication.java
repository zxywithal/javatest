package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.example.*")
@EnableHystrix
public class ProductApplication {

    @Bean("restTemplate")
    @LoadBalanced
    public RestTemplate getRestTemp(){
        return new RestTemplate();
    }
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
