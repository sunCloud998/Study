package com.sfy.java.thread.semaphore;

import com.sfy.java.thread.lock.*;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/22
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++){
            threads[i] = new Thread(new Job(printQueue),"Thread"+i);
        }
        for(int i=0;i<10;i++){
            threads[i].start();
        }
    }

}
