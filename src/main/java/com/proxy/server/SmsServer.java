package com.proxy.server;
/**
 *  代理 模式
 * @author gf
 * @date 2021/7/27
 */
public interface SmsServer {

    /**
     * send
     * @param message message
     */
    void send(String message);

    /**
     * send message
     * @param message message
     * @param channel channel
     * @return channel
     */
    String sendMessage(String message, String channel);
}
