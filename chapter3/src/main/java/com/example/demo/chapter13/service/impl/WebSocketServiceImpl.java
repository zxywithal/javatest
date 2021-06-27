package com.example.demo.chapter13.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by zhangxiaoyun3 on 2018/12/2.
 */
@Service
@ServerEndpoint("/ws")
public class WebSocketServiceImpl {
    public static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocket = new CopyOnWriteArraySet<>();
    private Session session;
    private static Log log = LogFactory.getLog(WebSocketServiceImpl.class);
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        addOnlineCount();
        this.webSocket.add(this);
        log.info("有新链接加入了，当前链接 " + getOnlineCount());
        try {
            sendMessage("有新连接加入了");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){
        webSocket.remove(this);
        subOnlineCount();
        log.info("有链接关闭了，当前链接 " + getOnlineCount());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("发生错误了");
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("客户端消息 ["+message+"]");
        try {
            for (WebSocketServiceImpl item : webSocket) {
                item.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void  addOnlineCount(){
        WebSocketServiceImpl.onlineCount++;
    }
    public synchronized static void  subOnlineCount(){
        WebSocketServiceImpl.onlineCount--;
    }

    public void sendMessage(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    private static int getOnlineCount(){
        return onlineCount;
    }
}
