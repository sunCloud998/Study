package com.sfy.java.designpattern.proxy.staticproxy;

/**
 * @function:
 * @description: TargetProxy.java
 * @date: 2019/07/12
 * @author: sunfayun
 * @version: 1.0
 */
public class TargetProxy implements Target {

    private Target target;

    public TargetProxy(Target target) {
        this.target = target;
    }

    @Override
    public void execute() {
        System.err.println("execute before.....");
        target.execute();
        System.err.println("execute after.....");
    }
}
