package com.sfy.java.thread.completionservice;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Function:
 * @Description: ReportGenerator.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class ReportGenerator implements Callable<String> {

    private String sender;
    private String title;

    public ReportGenerator(String sender,String title){
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() throws Exception {
        Long duration = (long)(Math.random() * 10);
        System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n",this.sender,this.title,duration);
        TimeUnit.SECONDS.sleep(duration);
        String ret = sender + ": " + title;
        return ret;
    }
}
