package com.sfy.java.thread;

/**
 * 测试多个线程嵌套的问题
 * @Description: MultiThreadCycle.java
 * @Author: sunfayun
 * @Date: 2015/08/12
 * @Time: 上午10:54
 * @Version 1.0
 */
public class MultiThreadCycle {

    public static void main(String[] args) {
        final MultiThreadCycle multiThreadCycle = new MultiThreadCycle();
        new Thread(new Runnable() {
            @Override
            public void run() {
                multiThreadCycle.sayHello();
            }
        });
    }

    public void sayHello(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                print("hello world");
            }
        });
    }

    private void print(String info){
        System.err.println("==>"+info);
    }

}
