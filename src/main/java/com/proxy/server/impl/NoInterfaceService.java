package com.proxy.server.impl;

import lombok.extern.slf4j.Slf4j;

/**
 * 未实现接口的类
 * @author gf
 * @date 2021/7/27
 */
@Slf4j
public class NoInterfaceService {

    public String send(String messages,String channel){
        return channel;
    }
}
