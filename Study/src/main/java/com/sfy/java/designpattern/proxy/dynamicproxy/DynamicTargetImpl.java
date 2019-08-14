package com.sfy.java.designpattern.proxy.dynamicproxy;

/**
 * @function:
 * @description: DynamicTargetImpl.java
 * @date: 2019/07/12
 * @author: sunfayun
 * @version: 1.0
 */
public class DynamicTargetImpl implements DynamicTarget {

    @Override
    public void execute() {
        System.err.println("executing........");
    }
}
