package com.sfy.java.thread;

/**
 * 测试多线程调用一个方法的情况，使用synchronized关键字来确保同一时间只会有一个线程执行该方法
 * @Description: SynchronizedTest.java
 * @Author: sunfayun
 * @Date: 2015/07/23
 * @Time: 下午12:12
 * @Version 1.0
 */
public class SynchronizedTest {

    public static void main(String[] args) throws InterruptedException {
        final SynchronizedTest synchronizedTest = new SynchronizedTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronizedTest.sayHello("子线程一");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronizedTest.sayHello("子线程二");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private synchronized void sayHello(String name) throws InterruptedException {
        System.err.println(name + "***输出内容开始***");
        Thread.sleep(5000);
        System.err.println(name + "***输出内容结束啦***");
    }
}
