package com.sfy.java.thread.concurrentlist;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];
        for(int i=0;i<100;i++){
            AddTask addTask = new AddTask(list);
            threads[i] = new Thread(addTask);
            threads[i].start();
        }
        System.out.println(threads.length+" AddTask threads have been launched");

        for(int i=0;i<threads.length;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" Main: Size of the list is :"+list.size());

        for(int i=0;i<threads.length;i++){
            PollTask pollTask = new PollTask(list);
            threads[i] = new Thread(pollTask);
            threads[i].start();
        }
        System.out.printf("Main %d PollTask threads have been launched\n",threads.length);

        for(int i=0;i<threads.length;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main: Size of the List:"+list.size());
    }

}
