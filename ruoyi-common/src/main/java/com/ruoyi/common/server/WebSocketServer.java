package com.ruoyi.common.server;

import org.apache.ibatis.javassist.tools.web.Webserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket服务器
 *
 * @author Zhenxi Chen
 * @date 2021/6/16
 */
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private static ConcurrentHashMap<String, WebSocketServer> webSockets =
            new ConcurrentHashMap<>();

    private Session session;        // 存储当前对话的session

    private String sid;         // 存储会话ID

    /**
     * 建立连接时的处理方法
     * @param session
     * @param sid 会话id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        this.sid = sid;
        webSockets.put(sid, this);
    }

    /**
     * 关闭连接时
     */
    @OnClose
    public void onClose() {
        webSockets.remove(sid, this);       // 从map中移除该对象，之后该对象会被GC机制回收
    }

    /**
     * 对接收到的消息进行响应
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {

    }

    /**
     * 出现错误时的响应
     */
    @OnError
    public void onError(Session session, Throwable error) {
        String errorMsg = session.getId() + "error: " + error.getMessage();
        log.error(errorMsg);
    }

    /**
     * 发送消息
     * @param message 发送消息的内容
     */
    public void send(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            this.session.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 发送一条消息
     * @param message 消息内容
     * @param sid 目标的sid
     */
    public static void sendMessage(String message, String sid) {
        WebSocketServer server = webSockets.get(sid);       // 获取对应的server
        server.send(message);
    }

    /**
     * 关闭连接
     * @param sid 会话ID
     */
    public static void closeSession(String sid) {
        WebSocketServer server = webSockets.get(sid);
        server.close();
    }

}
