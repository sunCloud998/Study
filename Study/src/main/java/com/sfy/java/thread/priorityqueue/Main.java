package com.sfy.java.thread.priorityqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/28
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
        Thread[] threads = new Thread[5];
        for(int i=0;i<5;i++){
            Task task = new Task(i,queue);
            threads[i] = new Thread(task);
        }
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }
        for(int i=0;i<threads.length;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main: Queue Size:"+queue.size());
        for(int i=0;i<threads.length*1000;i++){
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n",event.getThread(),event.getPriority());
        }
        System.out.printf("Main: Queue Size: %d\n",queue.size());
        System.out.printf("Main: End of the program\n");
    }

}
