package com.sfy.java.thread.executor;

import com.sfy.util.time.DateTimeUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Function:
 * @Description: Task.java
 * @Date: 2016/06/24
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Task implements Runnable {

    private String initTime;
    private String name;

    public Task(String name){
        this.initTime = DateTimeUtil.currentTimeString();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Task %s: Created on: %s\n",Thread.currentThread().getName(),name,initTime);
        System.out.printf("%s: Task %s: Started on: %s\n",Thread.currentThread().getName(),name,DateTimeUtil.currentTimeString());

        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Task %s: Doing a task during %dseconds\n",Thread.currentThread().getName(),name,duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Task %s: Finished on: %s\n",Thread.currentThread().getName(),name,DateTimeUtil.currentTimeString());
    }
}
