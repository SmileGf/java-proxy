package com.proxy.factory;

import com.proxy.interceptor.DebugInvocationHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 *  jdk动态代理工厂类 第三步
 * @author gf
 * @date 2021/7/27
 */
@Slf4j
public class JdkProxyFactory {

    /**
     * 通过为  Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类
     * @param target target
     * @return object
     */
    public static Object getProxy(Object target) {

        log.info("----------JdkProxyFactory----------------getProxy()--------------class: {} ",target);
        return Proxy.newProxyInstance(
                // 目标类的类加载
                target.getClass().getClassLoader(),
                // 代理需要实现的接口，可指定多个
                target.getClass().getInterfaces(),
                // 代理对象对应的自定义 InvocationHandler
                new DebugInvocationHandler(target)
        );
    }
}
