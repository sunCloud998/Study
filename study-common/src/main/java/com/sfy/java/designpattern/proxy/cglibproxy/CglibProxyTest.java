package com.sfy.java.designpattern.proxy.cglibproxy;

/**
 * @function:
 * @description: CglibProxyTest.java
 * @date: 2019/07/12
 * @author: sunfayun
 * @version: 1.0
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        CglibTarget cglibTarget = (CglibTarget) cglibProxy.getInstance(new CglibTarget());
        cglibTarget.execute();
    }

}
