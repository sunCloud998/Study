package com.sfy.java.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Function: 使用lock的方式来枷锁
 * @Description: PrintQueue.java
 * @Date: 2016/06/17
 * @Author: sunfayun
 * @Version: 1.0
 */
public class PrintQueue {

    private final Lock lock = new ReentrantLock();

    public void printJob(Object document){
        lock.lock();

        try {
            Long duration = (long)(Math.random()*1000);
            System.out.println(Thread.currentThread().getName()+":PrintQueue: Printing a Job during "+(duration/100)+" seconds");
            TimeUnit.MILLISECONDS.sleep(duration);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
