package com.sfy.java.thread.threadlocalrandom;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/30
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            TaskLocalRandom taskLocalRandom = new TaskLocalRandom();
            threads[i] = new Thread(taskLocalRandom);
            threads[i].start();
        }

    }

}
