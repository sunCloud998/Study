package com.sfy.java.thread.concurrentlist;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Function:
 * @Description: PollTask.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class PollTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for(int i=0;i<5000;i++){
            list.pollFirst();
            list.pollLast();
        }
    }
}
