package com.sfy.java.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @function:
 * @description: ArrayBlockingQueueTest.java
 * @date: 2019/06/27
 * @author: sunfayun
 * @version: 1.0
 */
public class ArrayBlockingQueueTest {

    private static AtomicInteger number = new AtomicInteger();

    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    private static ThreadPoolExecutor producer = new ThreadPoolExecutor(4,10,60, TimeUnit.SECONDS,new SynchronousQueue<>());
    private static ThreadPoolExecutor consumer = new ThreadPoolExecutor(2,10,60, TimeUnit.SECONDS,new SynchronousQueue<>());

    public static void main(String[] args) {
        for (int i=0;i<4;i++) {
            producer.execute(new Producer(number, queue));
        }

        for (int i=0;i<2;i++) {
            consumer.execute(new Consumer(queue));
        }
    }

    public static class Producer implements Runnable {

        private AtomicInteger number;
        private ArrayBlockingQueue<Integer> queue;

        public Producer(AtomicInteger number,ArrayBlockingQueue<Integer> queue) {
            this.number = number;
            this.queue = queue;
        }

        @Override
        public void run() {
            while (number.get() < 20) {
                try {
                    int value = number.incrementAndGet();
                    System.out.println("生产者添加数据：" + value);
                    queue.put(value);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class Consumer implements Runnable {

        private ArrayBlockingQueue<Integer> queue;

        public Consumer(ArrayBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!queue.isEmpty()) {
                try {
                    System.err.println("消费者消费数据：" + queue.take());
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


