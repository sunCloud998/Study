package com.sfy.java.thread.rejecttask;

import java.util.concurrent.TimeUnit;

/**
 * @Function:
 * @Description: Task.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Task "+name+": Starting");
        long duration = (long)(Math.random()*10);
        System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds\n",name,duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task %s: Ending\n",name);
    }

    @Override
    public String toString() {
        return name;
    }
}
