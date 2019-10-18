package com.sfy.java.zookeeper.curatorapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @function: 分布式锁测试
 * @description: DistributeLock.java
 * @date: 2019/08/15
 * @author: sunfayun
 * @version: 1.0
 */
public class DistributeLock {

    /**
     * 并发问题测试
     */
    @Test
    public void concurrentTest() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i=0;i<10;i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
                String number = simpleDateFormat.format(new Date());
                System.err.println("=>"+number);
            }).start();
        }
        countDownLatch.countDown();
    }

}
