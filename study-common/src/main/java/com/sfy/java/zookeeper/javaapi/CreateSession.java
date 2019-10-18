package com.sfy.java.zookeeper.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @function:
 * @description: CreateSession.java
 * @date: 2019/08/08
 * @author: sunfayun
 * @version: 1.0
 */
public class CreateSession {

    private final static String hostInfo = "127.0.0.1:2181";
    private final static Integer sessionTimeOut = 1000;
    private final static boolean canBeReadOnly = false;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void testGetState() throws Exception {
        ZooKeeper zk = new ZooKeeper(hostInfo, sessionTimeOut, null);
        System.err.println("=>"+zk.getState());
        zk.close();
    }

    @Test
    public void testCreateClient() throws Exception {
        ZooKeeper zk = new ZooKeeper(hostInfo, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.err.println("watchedEvent=>"+watchedEvent.toString());
                if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                    countDownLatch.countDown();
                    System.err.println("====连接成功======");
                }
            }
        },canBeReadOnly);
        System.err.println("1->"+zk.getState());
        countDownLatch.await();
        System.err.println("2->" + zk.getState().toString());
        zk.close();
    }



}
