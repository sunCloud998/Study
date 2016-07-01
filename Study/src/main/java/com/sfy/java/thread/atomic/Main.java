package com.sfy.java.thread.atomic;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/07/01
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();
        account.setBalance(1000);

        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        companyThread.start();
        bankThread.start();

        companyThread.join();
        bankThread.join();

        System.out.printf("Account: Final Balance:%d\n",account.getBalance());
    }

}
