package com.sfy.java.thread.atomic;

/**
 * @Function:
 * @Description: Bank.java
 * @Date: 2016/07/01
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Bank implements Runnable {

    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.subtractAmount(1000);
        }
    }
}
