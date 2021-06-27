package com.example.demo.chapter8.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.example.demo.chapter8.*")
@PropertySource(value = {"classpath:jdbc.properties",
        "classpath:application.properties",
        "classpath:cache.properties",
        "classpath:mongodb.properties"},ignoreResourceNotFound = false)
public class Chapter8Application {
	public static void main(String[] args) {
		SpringApplication.run(Chapter8Application.class, args);
	}
}
