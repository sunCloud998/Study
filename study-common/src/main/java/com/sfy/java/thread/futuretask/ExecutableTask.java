package com.sfy.java.thread.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Function:FutureTask类提供一个done()方法，允许你在执行者执行任务完成后执行一些代码。
 * 你可以用来做一些后处理操作，生成一个报告，通过e-mail发送结果，或释放一些资源。
 * @Description: ExecutableTask.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class ExecutableTask implements Callable<String> {

    private String name;

    public ExecutableTask(String name){
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        long duration = (long)(Math.random() * 10);
        System.err.printf("%s: Waiting %d seconds for results. \n",this.name,duration);
        TimeUnit.SECONDS.sleep(duration);
        return "Hello,World.I'm"+name;
    }

    public String getName() {
        return name;
    }
}
