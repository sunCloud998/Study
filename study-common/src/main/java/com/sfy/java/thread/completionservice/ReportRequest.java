package com.sfy.java.thread.completionservice;

import java.util.concurrent.CompletionService;

/**
 * @Function:
 * @Description: ReportRequest.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class ReportRequest implements Runnable {

    private String name;
    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator = new ReportGenerator(name,"Report");
        service.submit(reportGenerator);
    }
}
