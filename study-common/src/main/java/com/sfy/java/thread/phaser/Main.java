package com.sfy.java.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * @Function:
 * @Description: Main.java
 * @Date: 2016/06/23
 * @Author: sunfayun
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch system = new FileSearch("/Library","log",phaser);
        FileSearch apps = new FileSearch("/Applications","log",phaser);
        FileSearch documents = new FileSearch("/Users","log",phaser);

        Thread systemThread = new Thread(system,"System");
        systemThread.start();

        Thread appsThread = new Thread(apps,"Apps");
        appsThread.start();

        Thread documentThread = new Thread(documents,"Documents");
        documentThread.start();

        try {
            systemThread.join();
            appsThread.join();
            documentThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Terminated: "+phaser.isTerminated());
    }

}
