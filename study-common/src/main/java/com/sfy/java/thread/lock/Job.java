package com.sfy.java.thread.lock;

/**
 * @Function:
 * @Description: Job.java
 * @Date: 2016/06/17
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Job implements Runnable {

    private PrintQueue printQueue;

    public Job(PrintQueue printQueue){
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
    }
}
