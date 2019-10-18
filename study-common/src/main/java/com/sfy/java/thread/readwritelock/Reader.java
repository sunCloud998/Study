package com.sfy.java.thread.readwritelock;

/**
 * @Function:
 * @Description: Reader.java
 * @Date: 2016/06/17
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Reader implements Runnable {

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo){
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.printf("%s: price 1 : %f \n",Thread.currentThread().getName(),pricesInfo.getPrice1());
            System.out.printf("%s: price 2 : %f \n",Thread.currentThread().getName(),pricesInfo.getPrice2());
        }
    }
}
