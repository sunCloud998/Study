package com.sfy.java.thread.synchronize;

/**
 * @Function:
 * @Description: Company.java
 * @Date: 2016/06/16
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Company implements Runnable {

    private Account account;

    public Company(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            account.addAmount(1000);
        }
    }
}
