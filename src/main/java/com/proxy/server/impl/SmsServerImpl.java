package com.proxy.server.impl;

import com.proxy.server.SmsServer;
import lombok.extern.slf4j.Slf4j;

/**
 * 代理 测试类
 * @author  gf
 * @date 2021/7/27
 */
@Slf4j
public class SmsServerImpl implements SmsServer {

    @Override
    public void send(String message) {
        log.info("send message : {}",message);
    }

    @Override
    public String sendMessage(String message, String channel) {
        log.info("send message : {} | send channel :{}",message,channel);
        return channel;
    }
}
