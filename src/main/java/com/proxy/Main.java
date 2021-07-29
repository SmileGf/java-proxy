package com.proxy;

import com.proxy.factory.CglibProxyFactory;
import com.proxy.factory.JdkProxyFactory;
import com.proxy.server.SmsServer;
import com.proxy.server.impl.NoInterfaceService;
import com.proxy.server.impl.SmsServerImpl;
import lombok.extern.slf4j.Slf4j;

/**
 *  静态代理和动态代理比较：
 *      灵活性 ：动态代理更加灵活，不需要必须实现接口，可以直接代理实现类，并且可以不需要针对每个目标类都创建一个代理类。
 *          另外，静态代理中，接口一旦新增加方法，目标对象和代理对象都要进行修改，这是非常麻烦的
 *      JVM 层面 ：静态代理在编译时就将接口、实现类、代理类这些都变成了一个个实际的 class 文件。而动态代理是在运行时动态生成类字节码，并加载到 JVM 中的。
 *      
 *  jdk动态代理和cglib动态代理比较：
 *      JDK 动态代理只能代理实现了接口的类
 *      CGLIB 可以代理未实现任何接口的类
 *      CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，因此不能代理声明为 final 类型的类和方法
 *
 *      二者的效率来说，大部分情况都是 JDK 动态代理更优秀，随着 JDK 版本的升级，这个优势更加明显
 * @author gf
 * @date 2021/7/27
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("========================Static============================================Proxy============================================");
        /**
         * 静态代理
         */
        StaticProxy staticProxy = new StaticProxy(new SmsServerImpl());
        staticProxy.send("static proxy");
        staticProxy.sendMessage("static proxy","static");

        log.info("=====================Jdk=======================dynamic==============================proxy=================================");
        /**
         *  动态代理 jdk
         * 1.创建一个实现接口InvocationHandler的类，它必须实现invoke方法 DebugInvocationHandler
         * 2.创建被代理的类以及接口      SmsServer
         * 3.通过Proxy的静态方法  newProxyInstance(ClassLoader, Class[] interfaces, InvocationHandler h)创建一个代理    JdkProxyFactory
         * 4.通过代理调用方法   Main
         *  致命的问题是其只能代理实现了接口的类
         */
        // 实现接口的类
        SmsServer smsServer1 = (SmsServer) JdkProxyFactory.getProxy(new SmsServerImpl());
        smsServer1.send("dynamic proxy jdk");
       smsServer1.sendMessage("实现了接口的类 dynamic proxy jdk","dynamic jdk");

//        NoInterfaceService interfaceService = (NoInterfaceService)JdkProxyFactory.getProxy(new NoInterfaceService());
//        interfaceService.send("未实现任何接口的类 ","jdk dynamic proxy");

        log.info("======================cglib=====================dynamic================================proxy==============================");

        /**
         * 动态代理 cglib
         * 1.创建一个实现接口 MethodInterceptor 的类，它必须实现 intercept 方法 DebugMethodInterceptor
         * 2.创建被代理的类以及接口(可代理未创建接口的类)      SmsServer / NoInterfaceService
         * 3.通过Proxy的静态方法  Enhancer 创建一个代理    CglibProxyFactory
         * 4.通过代理调用方法   Main
         * cglib jar包源：springFramework spring-core 里面的 cglib 包可用 或 cglib 单独引用 cglib 包
         */
        SmsServer smsServer2 = (SmsServer) CglibProxyFactory.getProxy(SmsServerImpl.class);
        smsServer2.send("dynamic proxy cglib");
        smsServer2.sendMessage("实现了接口的类 dynamic proxy cglib","dynamic cglib");

        NoInterfaceService interfaceService1 = (NoInterfaceService)CglibProxyFactory.getProxy(NoInterfaceService.class);
        interfaceService1.send("未实现任何接口的类 ","cglib dynamic proxy");
    }

}
