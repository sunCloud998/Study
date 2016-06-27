package com.sfy.java.thread.exchanger;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Function:
 * @Description: Core.java
 * @Date: 2016/06/24
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Core {

    public static void main(String[] args) {
        List<String> productBuffer = Lists.newArrayList();
        List<String> consumerBuffer = Lists.newArrayList();

        Exchanger<List<String>> exchanger = new Exchanger<>();

        Producer producer = new Producer(productBuffer,exchanger);
        Consumer consumer = new Consumer(consumerBuffer,exchanger);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }

}
