package com.sfy.java.thread.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Function:
 * @Description: Account.java
 * @Date: 2016/07/01
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Account {

    private AtomicLong balance;

    public Account() {
        balance = new AtomicLong();
    }

    public long getBalance(){
        return balance.get();
    }

    public void setBalance(long balance){
        this.balance.set(balance);
    }

    public void addAccount(long amount){
        this.balance.getAndAdd(amount);
    }

    public void subtractAmount(long amount){
        this.balance.getAndAdd(-amount);
    }
}
