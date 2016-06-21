package com.sfy.java.thread.synchronize;

/**
 * @Function:
 * @Description: Bank.java
 * @Date: 2016/06/16
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Bank implements Runnable {

    private Account account;

    public Bank(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            account.subtractAmount(1000);
        }
    }
}
