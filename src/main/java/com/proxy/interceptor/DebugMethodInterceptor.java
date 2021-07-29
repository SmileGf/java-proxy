package com.proxy.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 动态代理 cglib
 * 自定义 MethodInterceptor
 * @author gf
 * @date 2021/7/27
 */
@Slf4j
public class DebugMethodInterceptor implements MethodInterceptor {

    /**
     *
     * @param o 被代理的对象（需要增强的对象）
     * @param method    被拦截的方法（需要增强的方法）
     * @param objects   方法入参
     * @param methodProxy   用于调用原始方法
     * @return  object
     * @throws Throwable throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //调用方法之前， 添加 操作
        log.info("-------------before method: {}", method);
        Object object = methodProxy.invokeSuper(o, objects);
        //调用方法之后，我们同样可以添加自己的操作
        log.info("------------after method : {} ", method.getName());
        return object;
    }
}
