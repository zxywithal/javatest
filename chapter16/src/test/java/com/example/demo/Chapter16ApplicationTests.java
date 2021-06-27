package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter16ApplicationTests {

	@Test
	public void contextLoads() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 3, 0);
        String status = null;
        status = Optional.ofNullable(status).orElse("300");
        System.out.println(status);

    }

}
