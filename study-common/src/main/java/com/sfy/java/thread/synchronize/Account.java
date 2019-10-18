package com.sfy.java.thread.synchronize;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * @Function:账户对象
 * @Description: Account.java
 * @Date: 2016/06/16
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Account {

    @Setter @Getter
    private double balance;

    public Account(){}

    public Account(double balance){
        this.balance = balance;
    }

    public synchronized void addAmount(double amount){
        double temp = balance;
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        temp += amount;
        balance = temp;
    }

    public synchronized void subtractAmount(double amount){
        double temp = balance;
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        temp -= amount;
        balance = temp;
    }

}
