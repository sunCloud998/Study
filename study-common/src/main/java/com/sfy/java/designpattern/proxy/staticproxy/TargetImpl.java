package com.sfy.java.designpattern.proxy.staticproxy;

/**
 * @function:
 * @description: DynamicTargetImpl.java
 * @date: 2019/07/12
 * @author: sunfayun
 * @version: 1.0
 */
public class TargetImpl implements Target {
    @Override
    public void execute() {
        System.err.println("executing......");
    }
}
