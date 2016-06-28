package com.sfy.java.thread.futuretask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
        ResultTask resultTask[] = new ResultTask[5];
        for(int i=0;i<5;i++){
            ExecutableTask executableTask = new ExecutableTask("Task "+i);
            resultTask[i] = new ResultTask(executableTask);
            executor.submit(resultTask[i]);
        }
        TimeUnit.SECONDS.sleep(5);
        for(int i=0;i<resultTask.length;i++){
            resultTask[i].cancel(true);
        }
        for(int i=0;i<resultTask.length;i++){
            if(!resultTask[i].isCancelled()){
                System.err.printf("%s\n",resultTask[i].get());
            }
        }
        executor.shutdown();
    }

}
