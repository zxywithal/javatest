package com.example.springboottest;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Slf4j
class SpringboottestApplicationTests {

    @Test
    void contextLoads() {

        MDC.put("uuid", UUID.randomUUID().toString());
        final TestLog testLog = new TestLog("hhhhh", "测试测试");
        log.debug("jfkdajfkldsa{}","123");
        log.info("jfkdajfkldsa{}","123");
        log.info("jfkdajfkldsa {} 第二个占位符 {}","123",testLog);
        log.info("jfkdajfkldsa {} 第二个占位符 {} 第三个占位符 {}","123",testLog,testLog);
        final Exception ex = new Exception("哈哈哈哈哈");
        log.error("faile {}",ex);
    }

    public static class TestLog{
        private String logName;
        private String logContent;

        public TestLog() {
        }

        public TestLog(String logName, String logContent) {
            this.logName = logName;
            this.logContent = logContent;
        }

        @Override
        public String toString() {
            return "TestLog{" +
                    "logName='" + logName + '\'' +
                    ", logContent='" + logContent + '\'' +
                    '}';
        }
    }

}
