package com.proxy.factory;

import com.proxy.interceptor.DebugMethodInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;

/**
 * cglib动态代理工厂类
 * @author gf
 * @date 2021/7/27 
 */
@Slf4j
public class CglibProxyFactory  {

    public static Object getProxy(Class<?> target) {
        log.info("----------CglibProxyFactory----------------getProxy()--------------class : {}",target);

        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();

        // 设置类加载器
        enhancer.setClassLoader(target.getClassLoader());

        // 设置代理类
        enhancer.setSuperclass(target);

        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());

        // 创建代理类
        return enhancer.create();
    }
}
