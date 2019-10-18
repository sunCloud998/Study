package com.sfy.java.thread.scheduled;

import com.sfy.util.time.DateTimeUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        System.err.printf("Main: Starting at: %s\n", DateTimeUtil.currentTimeString());

        Task task = new Task("Task");
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task,1,2, TimeUnit.SECONDS);
//        for(int i=0;i<10;i++){
//            System.err.printf("Main: Delay: %d\n",result.getDelay(TimeUnit.MILLISECONDS));
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.printf("Main: Finished at:%s\n",DateTimeUtil.currentTimeString());
    }

}
