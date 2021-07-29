package com.proxy.interceptor;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理
 * 通过实现 InvocationHandler 接口创建自己的调用处理器
 * @author gf
 * @date 2021/7/27
 */
@Slf4j
public class DebugInvocationHandler implements InvocationHandler {
    /**
     *  代理类中的真实对象
     */
    private final Object target;

    /**
     *  构造方法，给代理的真实对象赋初值
     * @param target target
     */
    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 该方法负责集中处理动态代理类上的所有方法调用。
     * 调用处理器根据这三个参数进行预处理或分派到委托类实例上反射执行
     * @param proxy     代理类的实例
     * @param method    被调用的方法对象
     * @param args      调用参数
     * @return  object
     * @throws Throwable throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在代理真实对象前 可添加 操作
        log.info("-------------before method: {}", method);

        // 代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object result = method.invoke(target, args);

        // 代理真实对象后 添加一些 操作
        log.info("------------after method : {} ", method.getName());
        return result;
    }
}
