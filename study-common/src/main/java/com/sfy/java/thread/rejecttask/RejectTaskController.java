package com.sfy.java.thread.rejecttask;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Function:RejectedExecutionHandler在调用shutdown()后，不接受新的任务。
 * rejectedExecution每个被执行者拒绝的任务都会调用这个方法。
 * 必须使用Executor类的setRejectedExecutionHandler()方法设置拒绝任务的处理器。
 * @Description: RejectTaskController.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class RejectTaskController implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("RejectedTaskController: The task %s has been rejected\n",r.toString());
        System.out.printf("RejectedTaskController: %s\n",executor.toString());
        System.out.printf("RejectedTaskController: Terminating:%s\n",executor.isTerminating());
        System.out.printf("RejectedTaksController: Terminated:%s\n",executor.isTerminated());
    }
}
