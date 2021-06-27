package com.example.demo.chapter10.main;

import com.example.demo.chapter10.interceptor.Interceptor1;
import com.example.demo.chapter10.interceptor.MulitiInterceptor1;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication(scanBasePackages = "com.example.demo.chapter10")
@MapperScan(basePackages = "com.example.demo.chapter10.dao",annotationClass = Mapper.class)
public class Chapter10Application implements WebMvcConfigurer{

    private LocaleChangeInterceptor localeChangeInterceptor;

    @Bean("localeResolver")
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sr = new SessionLocaleResolver();
        sr.setDefaultLocale(Locale.US);
        return sr;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        if(localeChangeInterceptor!=null)
            return localeChangeInterceptor;
        localeChangeInterceptor = new LocaleChangeInterceptor();
        //设置获取参数
        localeChangeInterceptor.setParamName("language");
        return  localeChangeInterceptor;
    }



	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截器在执行处理器前方法(preHandle)将请求的国际区域根据参数修改为对应的区域
		InterceptorRegistration r1 = registry.addInterceptor(new Interceptor1());
		r1.addPathPatterns("/interceptor/*");
		InterceptorRegistration r2 = registry.addInterceptor(new MulitiInterceptor1());
		r2.addPathPatterns("/interceptor/*");
        //注册本地化拦截器
		registry.addInterceptor(localeChangeInterceptor());
    }

	public static void main(String[] args) {
		SpringApplication.run(Chapter10Application.class, args);
	}
}
