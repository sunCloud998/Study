package com.sfy.java.thread.priorityqueue;

import com.google.common.collect.ComparisonChain;

/**
 * @Function:
 * @Description: Event.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Event implements Comparable<Event> {

    private int thread;
    private int priority;

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Event o) {
        return ComparisonChain
                .start()
                .compare(o.priority,this.priority)
                .result();
    }
}
