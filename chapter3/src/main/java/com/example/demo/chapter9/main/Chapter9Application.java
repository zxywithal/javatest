package com.example.demo.chapter9.main;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(scanBasePackages = "com.example.demo.chapter9")
@MapperScan(basePackages = "com.example.demo.chapter9.dao",annotationClass = Mapper.class)
public class Chapter9Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter9Application.class, args);
	}
}
