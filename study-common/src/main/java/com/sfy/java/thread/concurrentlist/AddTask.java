package com.sfy.java.thread.concurrentlist;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Function:
 * @Description: AddTask.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class AddTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for(int i=0;i<10000;i++){
            list.add(name+i);
        }
    }
}
