//package com.example.demo.chapter12.main;
//
//import org.apache.ibatis.annotations.Mapper;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
//import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//
///**
// * Created by zhangxiaoyun3 on 2018/12/11.
// */
//@SpringBootApplication(scanBasePackages = "com.example.demo.chapter12.*")
//@EnableCaching
//@PropertySource(value = {"classpath:security.properties","classpath:jdbc.properties","classpath:cache.properties"})
//@MapperScan(basePackages = "com.example.demo.chapter12.dao",annotationClass = Mapper.class)
//public class Chapter12Application extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//    @Value("${system.user.password.secret}")
//    private String secret = null;
//    @Autowired
//    private UserDetailsService uerDetailsService;
//    @Autowired
//    private RedisTemplate redisTemplate = null;
//
//    String pwdQuery = "select * from t_user where user_name = ?";
//    String roleQuery = "select u.user_name,r.role_name from t_role r,t_user u,t_user_role ur where u.user_name=? and u.id=ur.user_id and ur.role_id = r.id;";
////    @Bean
////    public PasswordEncoder getEncoder(){
////        return new BCryptPasswordEncoder();
////    }
//
//    @PostConstruct
//    public void initRedisTemplate() {
//        RedisSerializer<String> strSerializer = redisTemplate.getStringSerializer();
//        redisTemplate.setKeySerializer(strSerializer);
//        redisTemplate.setHashKeySerializer(strSerializer);
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("123456"));
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/user/welcome", "user/details")
//                .hasAnyRole("USER", "ADMIN")
//                .antMatchers("/admin/**")
//                .hasRole("ADMIN")
//                .anyRequest().permitAll();
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(secret);
//        auth.userDetailsService(uerDetailsService).passwordEncoder(passwordEncoder);
//
//        //=====================================
////        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> jdbcAu = auth.jdbcAuthentication();
////        jdbcAu.dataSource(dataSource);
////        jdbcAu.passwordEncoder(passwordEncoder);
////        jdbcAu.usersByUsernameQuery(pwdQuery);
////        jdbcAu.authoritiesByUsernameQuery(roleQuery);
//
//
//        //===================================================
////        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userconfig = auth.inMemoryAuthentication();
////        userconfig.passwordEncoder(passwordEncoder);
////        userconfig
////                .withUser("admin")
////                .password(passwordEncoder.encode("12345678"))
////                .roles("USER", "ADMIN");
////        userconfig
////                .withUser("myuser")
////                .password(passwordEncoder.encode("12345678"))
////                .roles("USER");
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(Chapter12Application.class);
//    }
//}
