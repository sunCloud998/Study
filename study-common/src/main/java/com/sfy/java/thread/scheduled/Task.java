package com.sfy.java.thread.scheduled;

import com.sfy.util.time.DateTimeUtil;

import java.util.concurrent.Callable;

/**
 * @Function:
 * @Description: Task.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Task implements Runnable{
    private String name;

    public Task(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Starting at : %s\n",name, DateTimeUtil.currentTimeString());
    }
}
