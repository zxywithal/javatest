package com.example.demo.chapter7.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiaoyun3 on 2018/11/10.
 */
@Component
public class RedisMessageListener implements MessageListener {
    private Log log = LogFactory.getLog(RedisMessageListener.class);
    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        String messageStr = new String(message.getBody());
        String topic = new String(pattern);
        log.info("messageStr "+messageStr);
        log.info("topic "+topic);
    }
}
