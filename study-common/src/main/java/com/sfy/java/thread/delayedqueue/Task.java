package com.sfy.java.thread.delayedqueue;

import com.sfy.java.thread.priorityqueue.*;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * @Function:
 * @Description: Task.java
 * @Date: 2016/06/29
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Task implements Runnable {

    private int id;
    private DelayQueue<Event> queue;

    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime()+(id*1000));
        System.out.printf("Thread %s: %s\n",id,delay);

        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            queue.add(event);
        }
    }
}
