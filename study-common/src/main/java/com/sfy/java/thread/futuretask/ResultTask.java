package com.sfy.java.thread.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Function:
 * @Description: ResultTask.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class ResultTask extends FutureTask<String> {

    private String name;

    public ResultTask(Callable<String> callable){
        super(callable);
        this.name = ((ExecutableTask)callable).getName();
    }

    @Override
    protected void done() {
        if(isCancelled()){
            System.err.printf("%s: Has been canceled \n",name);
        } else {
            System.err.printf("%s: Has finished \n",name);
        }
    }
}
