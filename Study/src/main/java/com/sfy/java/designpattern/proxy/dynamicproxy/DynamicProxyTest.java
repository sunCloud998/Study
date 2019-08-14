package com.sfy.java.designpattern.proxy.dynamicproxy;

/**
 * @function:
 * @description: DynamicProxyTest.java
 * @date: 2019/07/12
 * @author: sunfayun
 * @version: 1.0
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler();
        DynamicTarget dynamicTarget = (DynamicTarget) dynamicProxyHandler.getInstance(new DynamicTargetImpl());
        dynamicTarget.execute();
    }

}
