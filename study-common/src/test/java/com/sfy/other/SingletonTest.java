package com.sfy.other;

/**
 * @Description: SingletonTest.java
 * @User: sunfayun
 * @Date: 2017/04/26
 * @Version: 1.0
 */
public class SingletonTest {

    private SingletonTest(){

    }

    /**
     * 饿汉式
     */
    private static final SingletonTest SINGLETON_TEST = new SingletonTest();
    public SingletonTest getInstance01(){
        return SINGLETON_TEST;
    }

    /**
     * 懒汉式
     */
    private static SingletonTest singletonTest;
    public static synchronized SingletonTest getInstance02(){
        if(null == singletonTest){
            singletonTest = new SingletonTest();
        }
        return singletonTest;
    }

}
