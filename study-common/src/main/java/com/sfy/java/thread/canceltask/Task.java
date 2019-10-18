package com.sfy.java.thread.canceltask;

import java.util.concurrent.Callable;

/**
 * @Function:取消已经提交给执行者的任务
 * @Description: Task.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        while (true){
            System.err.println("Task is running....");
            Thread.sleep(100);
        }
    }
}
