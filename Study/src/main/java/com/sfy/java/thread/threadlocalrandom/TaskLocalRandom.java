package com.sfy.java.thread.threadlocalrandom;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Function:
 * @Description: TaskLocalRandom.java
 * @Date: 2016/06/30
 * @Author: sunfayun
 * @Version: 1.0
 */
public class TaskLocalRandom implements Runnable {

    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s : %d\n",name,ThreadLocalRandom.current().nextInt(10));
        }
    }
}
