package com.proxy;

import com.proxy.server.SmsServer;
import lombok.extern.slf4j.Slf4j;

/**
 * 静态代理  static proxy
 * @author gf
 * @date 2021/7/27
 */
@Slf4j
public class StaticProxy implements SmsServer {

    private final SmsServer smsServer;

    public StaticProxy(SmsServer smsServer){

        this.smsServer = smsServer;
    }

    @Override
    public void send(String message) {
        //方法调用前
        log.info("------SmsProxy : {}  -----------send------before----method-----",message);
        smsServer.send(message);
        log.info("------SmsProxy-----------send------after-----method----");
    }

    @Override
    public String sendMessage(String message, String channel) {
        return channel;
    }
}
