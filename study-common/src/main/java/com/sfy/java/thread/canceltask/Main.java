package com.sfy.java.thread.canceltask;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task task = new Task();
        System.err.println("Main:Executing the Task");
        Future<String> result = executor.submit(task);
        TimeUnit.SECONDS.sleep(2);
        System.err.println("Main: Canceling the Task");
        result.cancel(true);
        System.err.println("Main: Canceled:"+result.isCancelled());
        System.err.println("Main: Done:"+result.isDone());
        executor.shutdown();
        System.err.println("Main: The executor has finished");
    }

}
