package com.example.demo.chapter11.main;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.example.demo.chapter11")
@MapperScan(basePackages = "com.example.demo.chapter11.dao",annotationClass = Mapper.class)
@PropertySource({"classpath:jdbc.properties"})
public class Chapter11Application implements WebMvcConfigurer{


	public static void main(String[] args) {
		SpringApplication.run(Chapter11Application.class, args);
	}
}
