package com.sfy.java.designpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @function:
 * @description: DynamicProxyHandler.java
 * @date: 2019/07/12
 * @author: sunfayun
 * @version: 1.0
 */
public class DynamicProxyHandler implements InvocationHandler {

    private DynamicTarget target;

    public Object getInstance(DynamicTarget target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("execute before.........");
        method.invoke(target, args);
        System.err.println("execute after..........");
        return null;
    }
}
