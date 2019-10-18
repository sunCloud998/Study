package com.sfy.java.thread.delayedqueue;

import com.google.common.collect.ComparisonChain;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Function:
 * @Description: Event.java
 * @Date: 2016/06/29
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Event implements Delayed {

    private Date startDate;

    public Event(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Date now = new Date();
        long diff = startDate.getTime() - now.getTime();
        return unit.convert(diff,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return ComparisonChain
                .start()
                .compare(this.getDelay(TimeUnit.NANOSECONDS),o.getDelay(TimeUnit.NANOSECONDS))
                .result();
    }
}
