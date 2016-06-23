package com.sfy.java.thread.cyclicbarrier;

/**
 * @Function:
 * @Description: Grouper.java
 * @Date: 2016/06/23
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Grouper implements Runnable {

    private Results results;

    public Grouper(Results results){
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: Processing results...\n");
        int data[] = results.getData();
        for(int number : data){
            finalResult += number;
        }
        System.out.printf("Grouper: Total result: %d.\n",finalResult);
    }
}
