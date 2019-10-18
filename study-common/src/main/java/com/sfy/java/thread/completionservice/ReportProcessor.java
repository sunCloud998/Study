package com.sfy.java.thread.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Function:
 * @Description: ReportProcessor.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class ReportProcessor implements Runnable {

    private CompletionService<String> completionService;
    private boolean end;

    public ReportProcessor(CompletionService<String> completionService) {
        this.completionService = completionService;
        end = false;
    }

    @Override
    public void run() {
        while (!end){
            try {
                Future<String> result = completionService.poll(20, TimeUnit.SECONDS);
                if(null != result){
                    String report = result.get();
                    System.out.printf("ReportReceiver: Report Received:%s\n",report);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.printf("ReportSender: End\n");
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
