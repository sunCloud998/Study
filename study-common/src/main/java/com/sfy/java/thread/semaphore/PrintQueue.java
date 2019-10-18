package com.sfy.java.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Function:控制并发访问资源
 * @Description: PrintQueue.java
 * @Date: 2016/06/21
 * @Author: sunfayun
 * @Version: 1.0
 */
public class PrintQueue {

    private final Semaphore semaphore;

    public PrintQueue(){
        semaphore = new Semaphore(1);
    }

    public void printJob(Object object){
        try {
            semaphore.acquire();
            long duration = (long)(Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",Thread.currentThread().getName(),duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

}
