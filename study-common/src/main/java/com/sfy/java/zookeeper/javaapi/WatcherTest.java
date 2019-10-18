package com.sfy.java.zookeeper.javaapi;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @function:
 * @description: WatcherTest.java
 * @date: 2019/08/09
 * @author: sunfayun
 * @version: 1.0
 */
public class WatcherTest {

    private final static String hostInfo = "127.0.0.1:2181";
    private final static Integer sessionTimeOut = 1000;
    private final static boolean canBeReadOnly = false;
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private ZooKeeper zk;

    @Before
    public void init () {
        try {
            zk = new ZooKeeper(hostInfo, sessionTimeOut, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.err.println("1、event:"+watchedEvent.toString());
                    if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                        System.err.println("2、连接已建立，event:"+watchedEvent.toString());
                        countDownLatch.countDown();
                    }
                }
            }, canBeReadOnly);
            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExists() {
        try {
            zk.exists("/sfy-test", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.err.println("事件监听,eventType:"+watchedEvent.getType()+", event:"+watchedEvent);
                    // watcher一次性事件，需要再次注册监听
                    try {
                        zk.exists(watchedEvent.getPath(), true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {
        try {
            zk.create("/sfy-test", "sunCloud".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            zk.setData("/sfy-test", "sunCloud998".getBytes(), -1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            zk.delete("/sfy-test", -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWatcher() {
        this.testExists();
        this.testCreate();
        this.testUpdate();
        this.testDelete();
    }

}
