package org.example.jdk9;

/**
 * description: 接口私有方法测试
 * date: 2023/07/03
 * author: sunfayun
 * version: 1.0
 */
public interface PrivateMethodInterface {

    /**
     * 接口方法
     */
    void normalInterfaceMethod();

    /**
     * 默认方法
     */
    default void defaultMethod() {
        System.err.println("默认方法调用，该方法中调用了接口的私有方法");
        init();
    }

    /**
     * 私有方法
     */
    private void init() {
        System.err.println("接口的私有方法");
    }

}
