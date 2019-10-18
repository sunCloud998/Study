package com.sfy.java.designpattern.proxy.staticproxy;

/**
 * @function:
 * @description: TargetProxyTest.java
 * @date: 2019/07/12
 * @author: sunfayun
 * @version: 1.0
 */
public class TargetProxyTest {

    public static void main(String[] args) {
        Target target = new TargetImpl();
        TargetProxy targetProxy = new TargetProxy(target);
        targetProxy.execute();
    }

}
