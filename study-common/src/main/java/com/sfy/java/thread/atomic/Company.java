package com.sfy.java.thread.atomic;

/**
 * @Function:
 * @Description: Company.java
 * @Date: 2016/07/01
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Company implements Runnable {

    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.addAccount(1000);
        }
    }
}
