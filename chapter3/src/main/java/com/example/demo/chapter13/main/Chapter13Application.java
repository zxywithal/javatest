package com.example.demo.chapter13.main;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.example.demo.chapter13")
@MapperScan(basePackages = "com.example.demo.chapter13.dao",annotationClass = Mapper.class)
@PropertySource({"classpath:activemq.properties"})

public class Chapter13Application implements WebMvcConfigurer{
	public static void main(String[] args) {
		SpringApplication.run(Chapter13Application.class, args);
	}
}
