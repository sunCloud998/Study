package com.sfy.java.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Function:
 * @Description: CyclicBarrierTest.java
 * @Date: 2016/06/27
 * @Author: sunfayun
 * @Version: 1.0
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        //所有线程都到达统一的执行点以后执行CyclicBarrier中的run()方法
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.err.println("所有线程都到达,开始执行》》》》》");
            }
        });
        for(int i=0;i<3;i++){
            final int value = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Long duration = (long)(Math.random()*2000);
                    try {
                        Thread.sleep(duration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println("第 "+value + " 个线程到达,处于等待状态。。。");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
