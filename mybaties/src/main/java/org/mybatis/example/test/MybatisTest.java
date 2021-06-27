package org.mybatis.example.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MybatisTest {
    public static boolean typeConfirm(Object params){
      log.info("params type is {}",params);
      log.info("params type is {}",params.getClass().getName());
      return Boolean.TRUE;
    }
}
