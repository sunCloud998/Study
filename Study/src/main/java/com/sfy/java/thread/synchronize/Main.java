package com.sfy.java.thread.synchronize;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/16
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);

//        Company company = new Company(account);//使用同一个对象调用synchronized修饰的方法,其他线程会阻塞
        Company company = new Company(new Account(1000));//线程不会阻塞
        Thread companyThread = new Thread(company);

        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        System.out.printf("Account : Initial Balance: %f\n",account.getBalance());

        companyThread.start();
        bankThread.start();

        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance: %f\n",account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
